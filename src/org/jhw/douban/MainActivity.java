package org.jhw.douban;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jhw.douban.adapter.SongAdapter;
import org.jhw.douban.constants.RequestUrls;
import org.jhw.douban.http.HttpHelper.HttpException;
import org.jhw.douban.http.HttpManager;
import org.jhw.douban.media.IMediaPlaybackService;
import org.jhw.douban.media.MediaPlaybackService;
import org.jhw.douban.model.DoubanFMObj;
import org.jhw.douban.model.DoubanFMSongObj;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {
	private static final String TAG = "douban";

	private String resultData = "";
	private ListView listView;
	private DoubanFMObj fmObj;
	
	private static IMediaPlaybackService sService;
	public MediaPlayer mediaPlayer;
	public ServiceBinder conn;
	public HashMap<String, DoubanFMSongObj> map = new HashMap<String, DoubanFMSongObj>();
	public List<DoubanFMSongObj> list = new ArrayList<DoubanFMSongObj>();
	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			Log.i(TAG, "list :" + fmObj.getSong().isEmpty());
			SongAdapter adapter = new SongAdapter(MainActivity.this, fmObj.getSong(),listView);
			listView.setAdapter(adapter);
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.song_list);
		listView.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long id) {
						if (listView != null) {
							DoubanFMSongObj songObj = (DoubanFMSongObj) listView.getItemAtPosition(position);
							playUrl(songObj.getUrl());
						}
					}
		});
		conn = new ServiceBinder();
		bindService(new Intent(this,MediaPlaybackService.class), conn, Context.BIND_AUTO_CREATE);
		
//		mediaPlayer = new MediaPlayer();
//		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		Thread thread = new Thread(new AccessNetworkThread());
		thread.start();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unbindService(conn);
	}
	
	private static class ServiceBinder implements ServiceConnection {

		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			sService = IMediaPlaybackService.Stub.asInterface(service);
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			sService = null;
		}
		
	}
	
	public void itemPlayPauseClicked(View view) {
		Log.i(TAG, "itemPlayPauseClicked ...");
	}

	public void playPauseClicked(View view) {
		updatePlayPause();
	}

	public void play(View view) {
		if (sService == null) {
			return;
		}
		try {
			if (!sService.isPlaying()) {
				sService.play();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pause(View view) {
		if (sService == null) {
			return;
		}
		try {
			if (sService.isPlaying()) {
				sService.pause();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public void refresh(View view) {
		Thread thread = new Thread(new AccessNetworkThread());
		thread.start();
	}
	
	private void playUrl(String url) {
		try {
//			if (sService.isPlaying()) {
//				sService.stop();
//			}
			sService.playUrl(url);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void updatePlayPause() {
		ImageButton b = (ImageButton) findViewById(R.id.playpause);
		if (b != null) {
			if (mediaPlayer.isPlaying()) {
				b.setImageResource(R.drawable.btn_playback_ic_pause_small);
			} else {
				b.setImageResource(R.drawable.btn_playback_ic_play_small);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	class AccessNetworkThread implements Runnable {

		public void run() {
			try {
				fmObj = HttpManager.getInstance().getResponseInJson(RequestUrls.DEF_PlAYLIST_M, DoubanFMObj.class);
				if (fmObj != null) {
					handler.sendEmptyMessage(0);
				}
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
