package com.sizu.mingteng.my_xianglekang.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sizu.mingteng.my_xianglekang.BR;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/6/1.
 */

public class MovieBean extends BaseObservable  implements Serializable{

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    @Bindable
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        notifyPropertyChanged(BR.count);

    }

    @Bindable
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
        notifyPropertyChanged(BR.start);
    }

    @Bindable
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        notifyPropertyChanged(BR.total);

    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    @Bindable
    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
        notifyPropertyChanged(BR.subjects);
    }

    public static class SubjectsBean extends BaseObservable  implements Serializable {
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

        @Bindable
        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
            notifyPropertyChanged(BR.rating);
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
            notifyPropertyChanged(BR.title);
        }

        @Bindable
        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
            notifyPropertyChanged(BR.collect_count);
        }

        @Bindable
        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
            notifyPropertyChanged(BR.original_title);
        }

        @Bindable
        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
            notifyPropertyChanged(BR.subjects);
        }

        @Bindable
        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
            notifyPropertyChanged(BR.year);
        }

        @Bindable
        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
            notifyPropertyChanged(BR.images);
        }

        @Bindable
        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
            notifyPropertyChanged(BR.alt);
        }

        @Bindable
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            notifyPropertyChanged(BR.id);
        }

        @Bindable
        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
            notifyPropertyChanged(BR.genres);
        }

        @Bindable
        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
            notifyPropertyChanged(BR.casts);
        }

        @Bindable
        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
            notifyPropertyChanged(BR.directors);
        }

        public static class RatingBean extends BaseObservable  implements Serializable{
            /**
             * max : 10
             * average : 7.4
             * stars : 40
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            @Bindable
            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
                notifyPropertyChanged(BR.max);
            }

            @Bindable
            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
                notifyPropertyChanged(BR.average);
            }

            @Bindable
            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
                notifyPropertyChanged(BR.stars);
            }

            @Bindable
            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
                notifyPropertyChanged(BR.min);
            }
        }

        public static class ImagesBean extends BaseObservable  implements Serializable{
            /**
             * small : http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2459723975.jpg
             * large : http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2459723975.jpg
             * medium : http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2459723975.jpg
             */

            private String small;
            private String large;
            private String medium;

            @Bindable
            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
                notifyPropertyChanged(BR.small);
            }

            @Bindable
            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
                notifyPropertyChanged(BR.large);
            }

            @Bindable
            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
                notifyPropertyChanged(BR.medium);
            }
        }

        public static class CastsBean extends BaseObservable  implements Serializable{
            /**
             * alt : https://movie.douban.com/celebrity/1054456/
             * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/562.jpg","large":"http://img7.doubanio.com/img/celebrity/large/562.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/562.jpg"}
             * name : 约翰尼·德普
             * id : 1054456
             */

            private String alt;
            private AvatarsBean avatars;
            private String name;
            private String id;

            @Bindable
            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
                notifyPropertyChanged(BR.alt);
            }

            @Bindable
            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
                notifyPropertyChanged(BR.avatars);
            }

            @Bindable
            public String getName() {
                return name;
            }

            public void setName(String name) {
               this.name = name;
                notifyPropertyChanged(BR.name);
            }

            @Bindable
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
                notifyPropertyChanged(BR.id);
            }

            public static class AvatarsBean extends BaseObservable  implements Serializable{
                /**
                 * small : http://img7.doubanio.com/img/celebrity/small/562.jpg
                 * large : http://img7.doubanio.com/img/celebrity/large/562.jpg
                 * medium : http://img7.doubanio.com/img/celebrity/medium/562.jpg
                 */

                private String small;
                private String large;
                private String medium;

                @Bindable
                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                    notifyPropertyChanged(BR.small);
                }

                @Bindable
                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                    notifyPropertyChanged(BR.large);
                }

                @Bindable
                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                    notifyPropertyChanged(BR.medium);
                }
            }
        }

        public static class DirectorsBean extends BaseObservable  implements Serializable{
            /**
             * alt : https://movie.douban.com/celebrity/1019391/
             * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/58032.jpg","large":"http://img7.doubanio.com/img/celebrity/large/58032.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/58032.jpg"}
             * name : 艾斯彭·山德伯格
             * id : 1019391
             */

            private String alt;
            private AvatarsBeanX avatars;
            private String name;
            private String id;

            @Bindable
            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
                notifyPropertyChanged(BR.alt);
            }


            @Bindable
            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
                notifyPropertyChanged(BR.avatars);
            }

            @Bindable
            public String getName() {
                return name;
            }

            public void setName(String name) {
               this.name = name;
                notifyPropertyChanged(BR.name);
            }

            @Bindable
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
                notifyPropertyChanged(BR.id);
            }

            public static class AvatarsBeanX extends BaseObservable  implements Serializable{
                /**
                 * small : http://img7.doubanio.com/img/celebrity/small/58032.jpg
                 * large : http://img7.doubanio.com/img/celebrity/large/58032.jpg
                 * medium : http://img7.doubanio.com/img/celebrity/medium/58032.jpg
                 */

                private String small;
                private String large;
                private String medium;

                @Bindable
                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                    notifyPropertyChanged(BR.small);
                }

                @Bindable
                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                    notifyPropertyChanged(BR.large);
                }

                @Bindable
                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                    notifyPropertyChanged(BR.medium);
                }
            }
        }
    }
}
