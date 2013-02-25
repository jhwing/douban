package org.jhw.douban.media;

interface IMediaPlaybackService {
	boolean isPlaying();
    void stop();
    void pause();
    void play();
    void prev();
    void next();
    void playUrl(String url);
}