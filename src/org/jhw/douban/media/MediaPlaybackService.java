package org.jhw.douban.media;

import java.io.IOException;
import java.lang.ref.WeakReference;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.os.RemoteException;

public class MediaPlaybackService extends Service implements
		MediaPlayer.OnPreparedListener, 
		MediaPlayer.OnErrorListener,
		AudioManager.OnAudioFocusChangeListener{

	private MediaPlayer mMediaPlayer;

	private final IBinder mBinder = new ServiceStub(this);

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mMediaPlayer.setOnPreparedListener(this);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mMediaPlayer != null){
			mMediaPlayer.release();
			mMediaPlayer = null;
		} 
	}
	

	public boolean isPlaying() {
		return mMediaPlayer.isPlaying();
	}

	public void play() {
		mMediaPlayer.start();
	}

	public void stop() {
		mMediaPlayer.stop();
		mMediaPlayer.release();
	}

	public void pause() {
		mMediaPlayer.pause();
	}

	public void prev() {
		try {
			mMediaPlayer.reset();
			mMediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playUrl(String url) {
		try {
			mMediaPlayer.reset();
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setDataSource(url);
			mMediaPlayer.prepareAsync();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void next() {

	}

	static class ServiceStub extends IMediaPlaybackService.Stub {
		WeakReference<MediaPlaybackService> mService;

		ServiceStub(MediaPlaybackService service) {
			mService = new WeakReference<MediaPlaybackService>(service);
		}

		public boolean isPlaying() throws RemoteException {
			return mService.get().isPlaying();
		}

		public void stop() throws RemoteException {
			mService.get().stop();
		}

		public void pause() throws RemoteException {
			mService.get().pause();
		}

		public void play() throws RemoteException {
			mService.get().play();
		}

		public void prev() throws RemoteException {
			mService.get().prev();
		}

		public void next() throws RemoteException {
			mService.get().next();
		}

		public void playUrl(String url) throws RemoteException {
			mService.get().playUrl(url);
		}
	}

	public void onPrepared(MediaPlayer mp) {
		mp.start();
	}

	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onAudioFocusChange(int focusChange) {
		// TODO Auto-generated method stub
		
	}
}
