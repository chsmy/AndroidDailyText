package com.chs.androiddailytext.coordinatorlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * A {@link CoordinatorLayout} that implements {@link NestedScrollingChild}.
 * This means it can act as a nested scrolling child, and thus forward scroll events to parents.
 *
 * This is useful for nested Coordinators, e.g. for inner fragments in a parent activity. If this is used,
 * scrolls to the inner fragment will trigger scroll animations (e.g. {@code AppBarLayout}s, fabs, ...)
 * to the parent activity as well.
 *
 * This works by *not* reinventing the wheel and reusing the same nested scrolling logic implemented
 * by behaviors. There is a dummy view inside the sheet that is capable of getting nested scrolling
 * callbacks, and forward them to the *outer* behavior that they normally would never reach.
 */
public class NestedScrollCoordinatorLayout extends CoordinatorLayout implements NestedScrollingChild {
    private static final String TAG = NestedScrollCoordinatorLayout.class.getSimpleName();

    /**
     * Constant for {@link #setPassMode(int)}. When this is selected, scroll events are
     * passed to the parent stream and, at the same time, to this Coordinator childs.
     */
    public static final int PASS_MODE_BOTH = 0;

    /**
     * Constant for {@link #setPassMode(int)}. When this is selected, scroll events are
     * passed to the parent stream and, if not consumed, they go on to this Coordinator childs.
     */
    public static final int PASS_MODE_PARENT_FIRST = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PASS_MODE_BOTH, PASS_MODE_PARENT_FIRST})
    public @interface PassMode {}

    private NestedScrollingChildHelper helper;
    private DummyBehavior dummyBehavior;

    public NestedScrollCoordinatorLayout(Context context) {
        super(context); i();
    }

    public NestedScrollCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs); i();
    }

    public NestedScrollCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); i();
    }

    private void i() {
        helper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        // Add a dummy view that will receive inner touch events.
        View dummyView = new View(getContext());
        dummyBehavior = new DummyBehavior();
        // I *think* this is needed for dummyView to be identified as "topmost" and receive events
        // before any other view.
        ViewCompat.setElevation(dummyView, ViewCompat.getElevation(this));
        // Make sure it does not fit windows, or it will consume insets before the AppBarLayout.
        dummyView.setFitsSystemWindows(false);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setBehavior(dummyBehavior);
        addView(dummyView, params);
    }

    /**
     * Sets the pass mode for this coordinator.
     * @see #PASS_MODE_BOTH
     * @see #PASS_MODE_PARENT_FIRST
     *
     * @param mode desired pass mode for scroll events.
     */
    public void setPassMode(@PassMode int mode) {
        if (dummyBehavior != null) dummyBehavior.setPassMode(mode);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        helper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return helper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return helper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        helper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return helper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable @Size(value = 2) int[] offsetInWindow) {
        return helper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable @Size(value = 2) int[] consumed, @Nullable @Size(value = 2) int[] offsetInWindow) {
        return helper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return helper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return helper.dispatchNestedPreFling(velocityX, velocityY);
    }

    /**
     * This behavior is assigned to our dummy, MATCH_PARENT view inside this bottom sheet layout.
     * Through this behavior the dummy view can listen to touch/scroll events.
     * Our goal is to propagate them to the parent stream.
     *
     * It has to be done manually because by default CoordinatorLayouts don't propagate scroll events
     * to their parent. This is bad for CoordinatorLayouts inside other CoordinatorLayouts, since
     * the coordination works relies heavily on scroll events.
     *
     * @param <DummyView> make sure it's not a nested-scrolling-enabled view or this will break.
     */
    private static class DummyBehavior<DummyView extends View> extends CoordinatorLayout.Behavior<DummyView> {

        @PassMode private int mode = PASS_MODE_BOTH;
        private final int[] cache = new int[2];

        DummyBehavior() {}

        void setPassMode(@PassMode int mode) {
            this.mode = mode;
        }

        @Override
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull DummyView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
            NestedScrollCoordinatorLayout sheet = (NestedScrollCoordinatorLayout) coordinatorLayout;
            // If we want to catch, catch.
            return sheet.startNestedScroll(axes);
        }


        @Override
        public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull DummyView child, @NonNull View target, int type) {
            NestedScrollCoordinatorLayout sheet = (NestedScrollCoordinatorLayout) coordinatorLayout;
            sheet.stopNestedScroll();
        }

        @Override
            // When moving the finger up, dy is > 0.
        public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull DummyView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
            NestedScrollCoordinatorLayout sheet = (NestedScrollCoordinatorLayout) coordinatorLayout;
            if (mode == PASS_MODE_PARENT_FIRST) {
                sheet.dispatchNestedPreScroll(dx, dy, consumed, null);
            } else if (mode == PASS_MODE_BOTH) {
                // Don't let sheet consume the original int.
                cache[0] = consumed[0];
                cache[1] = consumed[1];
                sheet.dispatchNestedPreScroll(dx, dy, cache, null);
            }
        }


        @Override
        public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull DummyView child, @NonNull View target, float velocityX, float velocityY) {
            NestedScrollCoordinatorLayout sheet = (NestedScrollCoordinatorLayout) coordinatorLayout;
            boolean s = sheet.dispatchNestedPreFling(velocityX, velocityY);
            if (mode == PASS_MODE_PARENT_FIRST) {
                return s;
            }
            return false;
        }

        // onNestedScroll and onNestedFling are not needed.
    }
}