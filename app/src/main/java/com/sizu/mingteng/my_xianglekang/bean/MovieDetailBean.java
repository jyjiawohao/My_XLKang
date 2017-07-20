package com.sizu.mingteng.my_xianglekang.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sizu.mingteng.my_xianglekang.BR;

import java.util.List;

/**
 * 电影详情
 * <p/>
 * Created  on 16/6/28.
 */
public class MovieDetailBean extends BaseObservable {
    /**
     * max : 10
     * average : 7.4
     * stars : 40
     * min : 0
     */

    private RatingBean rating;
    /**
     * rating : {"max":10,"average":7.4,"stars":"40","min":0}
     * reviews_count : 292
     * wish_count : 14807
     * douban_site :
     * year : 2009
     * images : {"small":"http://img3.doubanio.com/view/movie_poster_cover/ipst/public/p494268647.jpg","large":"http://img3.doubanio.com/view/movie_poster_cover/lpst/public/p494268647.jpg","medium":"http://img3.doubanio.com/view/movie_poster_cover/spst/public/p494268647.jpg"}
     * alt : https://movie.douban.com/subject/1764796/
     * id : 1764796
     * mobile_url : https://movie.douban.com/subject/1764796/mobile
     * title : 机器人9号
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/1764796
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["动画","冒险","奇幻"]
     * collect_count : 70697
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054395/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/51597.jpg","large":"http://img3.doubanio.com/img/celebrity/large/51597.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/51597.jpg"},"name":"伊利亚·伍德","id":"1054395"},{"alt":"https://movie.douban.com/celebrity/1016673/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/3996.jpg","large":"http://img3.doubanio.com/img/celebrity/large/3996.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/3996.jpg"},"name":"詹妮弗·康纳利","id":"1016673"},{"alt":"https://movie.douban.com/celebrity/1017907/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/55994.jpg","large":"http://img3.douban.com/img/celebrity/large/55994.jpg","medium":"http://img3.douban.com/img/celebrity/medium/55994.jpg"},"name":"约翰·C·赖利","id":"1017907"},{"alt":"https://movie.douban.com/celebrity/1036321/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/42033.jpg","large":"http://img3.douban.com/img/celebrity/large/42033.jpg","medium":"http://img3.douban.com/img/celebrity/medium/42033.jpg"},"name":"克里斯托弗·普卢默","id":"1036321"}]
     * current_season : null
     * original_title : 9
     * summary : 机器人9号（伊利亚•伍德 Elijah Wood 饰）突然醒来，发现身边的世界充满危机，四处残败，一片末世景象。9号带着一个画有三个奇怪符号的圆形物体逃到街上，幸遇发明家机器人2号（马丁•兰道 Martin Landau 饰）给自己装上了声音，但2号却不幸被机器怪兽抓走。9号找到了老兵1号（克里斯托弗•普卢默 Christopher Plummer 饰）、机械工5号（约翰•雷利 John C. Reilly 饰）、疯癫画家6号（克里斯品•格拉夫 Crispin Glover 饰）和大力士8号（弗雷德•塔塔绍尔 Fred Tatasciore 饰）。9号与5号擅自出行援救2号，危急时被女武士7号（詹妮佛•康纳利 Jennifer Connelly 饰）救下，但无意中9号却令终极机器兽复活。带着自己从哪里来以及生存使命的问题，9号决定想尽办法制服机器兽，拯救全世界……©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1276787/","avatars":{"small":"http://img3.douban.com/img/celebrity/small/1351678808.44.jpg","large":"http://img3.douban.com/img/celebrity/large/1351678808.44.jpg","medium":"http://img3.douban.com/img/celebrity/medium/1351678808.44.jpg"},"name":"申·阿克","id":"1276787"}]
     * comments_count : 11460
     * ratings_count : 56612
     * aka : ["9：末世决战","九","Number 9","机器人9号"]
     */

    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    /**
     * small : http://img3.doubanio.com/view/movie_poster_cover/ipst/public/p494268647.jpg
     * large : http://img3.doubanio.com/view/movie_poster_cover/lpst/public/p494268647.jpg
     * medium : http://img3.doubanio.com/view/movie_poster_cover/spst/public/p494268647.jpg
     */

    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    /**
     * alt : https://movie.douban.com/celebrity/1054395/
     * avatars : {"small":"http://img3.doubanio.com/img/celebrity/small/51597.jpg","large":"http://img3.doubanio.com/img/celebrity/large/51597.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/51597.jpg"}
     * name : 伊利亚·伍德
     * id : 1054395
     */

    private List<CastsBean> casts;
    /**
     * alt : https://movie.douban.com/celebrity/1276787/
     * avatars : {"small":"http://img3.douban.com/img/celebrity/small/1351678808.44.jpg","large":"http://img3.douban.com/img/celebrity/large/1351678808.44.jpg","medium":"http://img3.douban.com/img/celebrity/medium/1351678808.44.jpg"}
     * name : 申·阿克
     * id : 1276787
     */

    private List<DirectorsBean> directors;
    private List<String> aka;

    @Bindable
    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
        notifyPropertyChanged(BR.rating);
    }

    @Bindable
    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
        notifyPropertyChanged(BR.reviews_count);
    }

    @Bindable
    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
        notifyPropertyChanged(BR.wish_count);
    }

    @Bindable
    public String getDouban_site() {
        return douban_site;

    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
        notifyPropertyChanged(BR.douban_site);
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
    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
        notifyPropertyChanged(BR.mobile_url);
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
    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
        notifyPropertyChanged(BR.do_count);
    }

    @Bindable
    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
        notifyPropertyChanged(BR.share_url);
    }

    @Bindable
    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
        notifyPropertyChanged(BR.seasons_count);
    }

    @Bindable
    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
        notifyPropertyChanged(BR.schedule_url);
    }

    @Bindable
    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
        notifyPropertyChanged(BR.episodes_count);
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
    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
        notifyPropertyChanged(BR.current_season);
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
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
        notifyPropertyChanged(BR.summary);
    }

    @Bindable
    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
        notifyPropertyChanged(BR.subtype);
    }

    @Bindable
    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
        notifyPropertyChanged(BR.comments_count);
    }

    @Bindable
    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
        notifyPropertyChanged(BR.ratings_count);
    }

    @Bindable
    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
        notifyPropertyChanged(BR.countries);
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

    @Bindable
    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
        notifyPropertyChanged(BR.aka);
    }

    public static class RatingBean extends BaseObservable {
        private double max;
        private double average;
        private String stars;
        private double min;

        @Bindable
        public double getMax() {
            return max;
        }

        public void setMax(double max) {
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
        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
            notifyPropertyChanged(BR.min);
        }
    }

    public static class ImagesBean extends BaseObservable {
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

    public static class CastsBean extends BaseObservable {
        private String alt;
        /**
         * small : http://img3.doubanio.com/img/celebrity/small/51597.jpg
         * large : http://img3.doubanio.com/img/celebrity/large/51597.jpg
         * medium : http://img3.doubanio.com/img/celebrity/medium/51597.jpg
         */

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

        public static class AvatarsBean extends BaseObservable {
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

    public static class DirectorsBean extends BaseObservable {
        private String alt;
        /**
         * small : http://img3.douban.com/img/celebrity/small/1351678808.44.jpg
         * large : http://img3.douban.com/img/celebrity/large/1351678808.44.jpg
         * medium : http://img3.douban.com/img/celebrity/medium/1351678808.44.jpg
         */

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

        public static class AvatarsBean extends BaseObservable {
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
