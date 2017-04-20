package com.zmj.qvod.module.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by matt on 2017/3/30.
 */

public class HotMovieBean implements Serializable {


    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

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

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean implements Serializable{
        /**
         * rating : {"max":10,"average":6.9,"stars":"35","min":0}
         * genres : ["动作","奇幻","冒险"]
         * title : 金刚：骷髅岛
         * casts : [{"alt":"https://movie.douban.com/celebrity/1004596/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/35625.jpg","large":"https://img3.doubanio.com/img/celebrity/large/35625.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/35625.jpg"},"name":"汤姆·希德勒斯顿","id":"1004596"},{"alt":"https://movie.douban.com/celebrity/1027194/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1392029372.12.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1392029372.12.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1392029372.12.jpg"},"name":"布丽·拉尔森","id":"1027194"},{"alt":"https://movie.douban.com/celebrity/1054408/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/31870.jpg","large":"https://img3.doubanio.com/img/celebrity/large/31870.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/31870.jpg"},"name":"塞缪尔·杰克逊","id":"1054408"}]
         * collect_count : 52808
         * original_title : Kong: Skull Island
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1326532/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1359526353.7.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1359526353.7.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1359526353.7.jpg"},"name":"乔丹·沃格特-罗伯茨","id":"1326532"}]
         * year : 2017
         * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2436030518.webp","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2436030518.webp","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2436030518.webp"}
         * alt : https://movie.douban.com/subject/26309788/
         * id : 26309788
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<DirectorsBean> directors;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
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

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
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

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public static class RatingBean implements Serializable{
            /**
             * max : 10
             * average : 6.9
             * stars : 35
             * min : 0
             */

            private int max;
            private double average;
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
        }

        public static class ImagesBean implements Serializable{
            /**
             * small : https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2436030518.webp
             * large : https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2436030518.webp
             * medium : https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2436030518.webp
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

        public static class CastsBean implements Serializable{
            /**
             * alt : https://movie.douban.com/celebrity/1004596/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/35625.jpg","large":"https://img3.doubanio.com/img/celebrity/large/35625.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/35625.jpg"}
             * name : 汤姆·希德勒斯顿
             * id : 1004596
             */

            private String alt;
            private AvatarsBean avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean implements Serializable{
                /**
                 * small : https://img3.doubanio.com/img/celebrity/small/35625.jpg
                 * large : https://img3.doubanio.com/img/celebrity/large/35625.jpg
                 * medium : https://img3.doubanio.com/img/celebrity/medium/35625.jpg
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

        public static class DirectorsBean implements Serializable{
            /**
             * alt : https://movie.douban.com/celebrity/1326532/
             * avatars : {"small":"https://img1.doubanio.com/img/celebrity/small/1359526353.7.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1359526353.7.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1359526353.7.jpg"}
             * name : 乔丹·沃格特-罗伯茨
             * id : 1326532
             */

            private String alt;
            private AvatarsBeanX avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX implements Serializable{
                /**
                 * small : https://img1.doubanio.com/img/celebrity/small/1359526353.7.jpg
                 * large : https://img1.doubanio.com/img/celebrity/large/1359526353.7.jpg
                 * medium : https://img1.doubanio.com/img/celebrity/medium/1359526353.7.jpg
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
