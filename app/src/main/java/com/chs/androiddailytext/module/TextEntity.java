package com.chs.androiddailytext.module;

import java.util.List;

/**
 * 作者：chs on 2017-11-28 16:34
 * 邮箱：657083984@qq.com
 */

public class TextEntity {

    /**
     * count : 100
     * start : 0
     * total : 40
     * subjects : [{"rating":{"max":10,"average":9.3,"details":{"1":11,"2":51,"3":1036,"4":6366,"5":16036},"stars":"50","min":0},"genres":["喜剧","动画","冒险"],"title":"寻梦环游记","casts":[{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp"},"name_en":"Anthony Gonzalez","name":"安东尼·冈萨雷斯","alt":"https://movie.douban.com/celebrity/1370411/","id":"1370411"},{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp"},"name_en":"Gael García Bernal","name":"盖尔·加西亚·贝纳尔","alt":"https://movie.douban.com/celebrity/1041009/","id":"1041009"},{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp"},"name_en":"Benjamin Bratt","name":"本杰明·布拉特","alt":"https://movie.douban.com/celebrity/1036383/","id":"1036383"}],"durations":["105分钟"],"collect_count":70344,"mainland_pubdate":"2017-11-24","has_video":false,"original_title":"Coco","subtype":"movie","directors":[{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp"},"name_en":"Lee Unkrich","name":"李·昂克里奇","alt":"https://movie.douban.com/celebrity/1022678/","id":"1022678"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp"},"name_en":"Adrian Molina","name":"阿德里安·莫利纳","alt":"https://movie.douban.com/celebrity/1370425/","id":"1370425"}],"pubdates":["2017-10-20(莫雷利亚电影节)","2017-11-22(美国)","2017-11-24(中国大陆)"],"year":"2017","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp"},"alt":"https://movie.douban.com/subject/20495023/","id":"20495023"},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]
     * title : 正在上映的电影-北京
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsEntity> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsEntity> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsEntity {
        /**
         * rating : {"max":10,"average":9.3,"details":{"1":11,"2":51,"3":1036,"4":6366,"5":16036},"stars":"50","min":0}
         * genres : ["喜剧","动画","冒险"]
         * title : 寻梦环游记
         * casts : [{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp"},"name_en":"Anthony Gonzalez","name":"安东尼·冈萨雷斯","alt":"https://movie.douban.com/celebrity/1370411/","id":"1370411"},{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp"},"name_en":"Gael García Bernal","name":"盖尔·加西亚·贝纳尔","alt":"https://movie.douban.com/celebrity/1041009/","id":"1041009"},{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp"},"name_en":"Benjamin Bratt","name":"本杰明·布拉特","alt":"https://movie.douban.com/celebrity/1036383/","id":"1036383"}]
         * durations : ["105分钟"]
         * collect_count : 70344
         * mainland_pubdate : 2017-11-24
         * has_video : false
         * original_title : Coco
         * subtype : movie
         * directors : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp"},"name_en":"Lee Unkrich","name":"李·昂克里奇","alt":"https://movie.douban.com/celebrity/1022678/","id":"1022678"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp"},"name_en":"Adrian Molina","name":"阿德里安·莫利纳","alt":"https://movie.douban.com/celebrity/1370425/","id":"1370425"}]
         * pubdates : ["2017-10-20(莫雷利亚电影节)","2017-11-22(美国)","2017-11-24(中国大陆)"]
         * year : 2017
         * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp"}
         * alt : https://movie.douban.com/subject/20495023/
         * id : 20495023
         */

        private RatingEntity rating;
        private String title;
        private int collect_count;
        private String mainland_pubdate;
        private boolean has_video;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesEntity images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsEntity> casts;
        private List<String> durations;
        private List<DirectorsEntity> directors;
        private List<String> pubdates;

        public RatingEntity getRating() {
            return rating;
        }

        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getMainland_pubdate() {
            return mainland_pubdate;
        }

        public void setMainland_pubdate(String mainland_pubdate) {
            this.mainland_pubdate = mainland_pubdate;
        }

        public boolean isHas_video() {
            return has_video;
        }

        public void setHas_video(boolean has_video) {
            this.has_video = has_video;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesEntity getImages() {
            return images;
        }

        public void setImages(ImagesEntity images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsEntity> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsEntity> casts) {
            this.casts = casts;
        }

        public List<String> getDurations() {
            return durations;
        }

        public void setDurations(List<String> durations) {
            this.durations = durations;
        }

        public List<DirectorsEntity> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsEntity> directors) {
            this.directors = directors;
        }

        public List<String> getPubdates() {
            return pubdates;
        }

        public void setPubdates(List<String> pubdates) {
            this.pubdates = pubdates;
        }

        public static class RatingEntity {
            /**
             * max : 10
             * average : 9.3
             * details : {"1":11,"2":51,"3":1036,"4":6366,"5":16036}
             * stars : 50
             * min : 0
             */

            private int max;
            private double average;
            private DetailsEntity details;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public DetailsEntity getDetails() {
                return details;
            }

            public void setDetails(DetailsEntity details) {
                this.details = details;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public static class DetailsEntity {
                /**
                 * 1 : 11
                 * 2 : 51
                 * 3 : 1036
                 * 4 : 6366
                 * 5 : 16036
                 */

                @com.google.gson.annotations.SerializedName("1")
                private int _$1;
                @com.google.gson.annotations.SerializedName("2")
                private int _$2;
                @com.google.gson.annotations.SerializedName("3")
                private int _$3;
                @com.google.gson.annotations.SerializedName("4")
                private int _$4;
                @com.google.gson.annotations.SerializedName("5")
                private int _$5;

                public int get_$1() {
                    return _$1;
                }

                public void set_$1(int _$1) {
                    this._$1 = _$1;
                }

                public int get_$2() {
                    return _$2;
                }

                public void set_$2(int _$2) {
                    this._$2 = _$2;
                }

                public int get_$3() {
                    return _$3;
                }

                public void set_$3(int _$3) {
                    this._$3 = _$3;
                }

                public int get_$4() {
                    return _$4;
                }

                public void set_$4(int _$4) {
                    this._$4 = _$4;
                }

                public int get_$5() {
                    return _$5;
                }

                public void set_$5(int _$5) {
                    this._$5 = _$5;
                }
            }
        }

        public static class ImagesEntity {
            /**
             * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp
             * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp
             * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsEntity {
            /**
             * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp"}
             * name_en : Anthony Gonzalez
             * name : 安东尼·冈萨雷斯
             * alt : https://movie.douban.com/celebrity/1370411/
             * id : 1370411
             */

            private AvatarsEntity avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsEntity getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsEntity avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsEntity {
                /**
                 * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp
                 * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp
                 * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsEntity {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp"}
             * name_en : Lee Unkrich
             * name : 李·昂克里奇
             * alt : https://movie.douban.com/celebrity/1022678/
             * id : 1022678
             */

            private AvatarsEntityX avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsEntityX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsEntityX avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsEntityX {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
