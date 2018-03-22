package com.chs.androiddailytext.http;

/**
 * 作者：chs on 2018-03-22 15:07
 * 邮箱：657083984@qq.com
 */

public class Repo {

    /**
     * current_user_url : https://api.github.com/user
     * current_user_authorizations_html_url : https://github.com/settings/connections/applications{/client_id}
     * authorizations_url : https://api.github.com/authorizations
     * code_search_url : https://api.github.com/search/code?q={query}{&page,per_page,sort,order}
     * commit_search_url : https://api.github.com/search/commits?q={query}{&page,per_page,sort,order}
     * emails_url : https://api.github.com/user/emails
     * emojis_url : https://api.github.com/emojis
     * events_url : https://api.github.com/events
     * feeds_url : https://api.github.com/feeds
     * followers_url : https://api.github.com/user/followers
     * following_url : https://api.github.com/user/following{/target}
     * gists_url : https://api.github.com/gists{/gist_id}
     * hub_url : https://api.github.com/hub
     * issue_search_url : https://api.github.com/search/issues?q={query}{&page,per_page,sort,order}
     * issues_url : https://api.github.com/issues
     * keys_url : https://api.github.com/user/keys
     * notifications_url : https://api.github.com/notifications
     * organization_repositories_url : https://api.github.com/orgs/{org}/repos{?type,page,per_page,sort}
     * organization_url : https://api.github.com/orgs/{org}
     * public_gists_url : https://api.github.com/gists/public
     * rate_limit_url : https://api.github.com/rate_limit
     * repository_url : https://api.github.com/repos/{owner}/{repo}
     * repository_search_url : https://api.github.com/search/repositories?q={query}{&page,per_page,sort,order}
     * current_user_repositories_url : https://api.github.com/user/repos{?type,page,per_page,sort}
     * starred_url : https://api.github.com/user/starred{/owner}{/repo}
     * starred_gists_url : https://api.github.com/gists/starred
     * team_url : https://api.github.com/teams
     * user_url : https://api.github.com/users/{user}
     * user_organizations_url : https://api.github.com/user/orgs
     * user_repositories_url : https://api.github.com/users/{user}/repos{?type,page,per_page,sort}
     * user_search_url : https://api.github.com/search/users?q={query}{&page,per_page,sort,order}
     */

    private String current_user_url;
    private String current_user_authorizations_html_url;
    private String authorizations_url;
    private String code_search_url;
    private String commit_search_url;
    private String emails_url;
    private String emojis_url;
    private String events_url;
    private String feeds_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String hub_url;
    private String issue_search_url;
    private String issues_url;
    private String keys_url;
    private String notifications_url;
    private String organization_repositories_url;
    private String organization_url;
    private String public_gists_url;
    private String rate_limit_url;
    private String repository_url;
    private String repository_search_url;
    private String current_user_repositories_url;
    private String starred_url;
    private String starred_gists_url;
    private String team_url;
    private String user_url;
    private String user_organizations_url;
    private String user_repositories_url;
    private String user_search_url;

    public String getCurrent_user_url() {
        return current_user_url;
    }

    public void setCurrent_user_url(String current_user_url) {
        this.current_user_url = current_user_url;
    }

    public String getCurrent_user_authorizations_html_url() {
        return current_user_authorizations_html_url;
    }

    public void setCurrent_user_authorizations_html_url(String current_user_authorizations_html_url) {
        this.current_user_authorizations_html_url = current_user_authorizations_html_url;
    }

    public String getAuthorizations_url() {
        return authorizations_url;
    }

    public void setAuthorizations_url(String authorizations_url) {
        this.authorizations_url = authorizations_url;
    }

    public String getCode_search_url() {
        return code_search_url;
    }

    public void setCode_search_url(String code_search_url) {
        this.code_search_url = code_search_url;
    }

    public String getCommit_search_url() {
        return commit_search_url;
    }

    public void setCommit_search_url(String commit_search_url) {
        this.commit_search_url = commit_search_url;
    }

    public String getEmails_url() {
        return emails_url;
    }

    public void setEmails_url(String emails_url) {
        this.emails_url = emails_url;
    }

    public String getEmojis_url() {
        return emojis_url;
    }

    public void setEmojis_url(String emojis_url) {
        this.emojis_url = emojis_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getFeeds_url() {
        return feeds_url;
    }

    public void setFeeds_url(String feeds_url) {
        this.feeds_url = feeds_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getHub_url() {
        return hub_url;
    }

    public void setHub_url(String hub_url) {
        this.hub_url = hub_url;
    }

    public String getIssue_search_url() {
        return issue_search_url;
    }

    public void setIssue_search_url(String issue_search_url) {
        this.issue_search_url = issue_search_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public String getKeys_url() {
        return keys_url;
    }

    public void setKeys_url(String keys_url) {
        this.keys_url = keys_url;
    }

    public String getNotifications_url() {
        return notifications_url;
    }

    public void setNotifications_url(String notifications_url) {
        this.notifications_url = notifications_url;
    }

    public String getOrganization_repositories_url() {
        return organization_repositories_url;
    }

    public void setOrganization_repositories_url(String organization_repositories_url) {
        this.organization_repositories_url = organization_repositories_url;
    }

    public String getOrganization_url() {
        return organization_url;
    }

    public void setOrganization_url(String organization_url) {
        this.organization_url = organization_url;
    }

    public String getPublic_gists_url() {
        return public_gists_url;
    }

    public void setPublic_gists_url(String public_gists_url) {
        this.public_gists_url = public_gists_url;
    }

    public String getRate_limit_url() {
        return rate_limit_url;
    }

    public void setRate_limit_url(String rate_limit_url) {
        this.rate_limit_url = rate_limit_url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getRepository_search_url() {
        return repository_search_url;
    }

    public void setRepository_search_url(String repository_search_url) {
        this.repository_search_url = repository_search_url;
    }

    public String getCurrent_user_repositories_url() {
        return current_user_repositories_url;
    }

    public void setCurrent_user_repositories_url(String current_user_repositories_url) {
        this.current_user_repositories_url = current_user_repositories_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getStarred_gists_url() {
        return starred_gists_url;
    }

    public void setStarred_gists_url(String starred_gists_url) {
        this.starred_gists_url = starred_gists_url;
    }

    public String getTeam_url() {
        return team_url;
    }

    public void setTeam_url(String team_url) {
        this.team_url = team_url;
    }

    public String getUser_url() {
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }

    public String getUser_organizations_url() {
        return user_organizations_url;
    }

    public void setUser_organizations_url(String user_organizations_url) {
        this.user_organizations_url = user_organizations_url;
    }

    public String getUser_repositories_url() {
        return user_repositories_url;
    }

    public void setUser_repositories_url(String user_repositories_url) {
        this.user_repositories_url = user_repositories_url;
    }

    public String getUser_search_url() {
        return user_search_url;
    }

    public void setUser_search_url(String user_search_url) {
        this.user_search_url = user_search_url;
    }
}
