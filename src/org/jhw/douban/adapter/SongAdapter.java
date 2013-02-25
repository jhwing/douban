package org.jhw.douban.adapter;

import java.util.List;

import org.jhw.douban.R;
import org.jhw.douban.http.AsyncImageLoader;
import org.jhw.douban.http.AsyncImageLoader.ImageCallback;
import org.jhw.douban.model.DoubanFMSongObj;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SongAdapter extends BaseAdapter {

	private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
	private ListView listView;
	private List<DoubanFMSongObj> list;
	private AsyncImageLoader asyncImageLoader;
	public SongAdapter(Context context, List<DoubanFMSongObj> list, ListView listView) {
		super();
		this.mInflater = LayoutInflater.from(context);
		this.list = list;
		this.listView = listView;
		asyncImageLoader = new AsyncImageLoader(context);
	}

	public int getCount() {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item, null);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		DoubanFMSongObj songObj = list.get(position);
		if (songObj != null && vh != null) {
			vh.getSong_name().setText(songObj.getTitle());
			vh.getArtist().setText(songObj.getArtist());
			ImageView imageView = vh.getImgView();
			imageView.setTag(songObj.getPicture());
			imageView.setImageDrawable(asyncImageLoader.loadDrawable(songObj.getPicture(), new ImageCallback(){
				public void imageLoaded(Drawable imageDrawable, String imageUrl) {
					Log.i("songadaper", imageUrl);
					ImageView imageViewByTag = (ImageView) listView.findViewWithTag(imageUrl);
					if (imageViewByTag != null) {
						imageViewByTag.setImageDrawable(imageDrawable);
					}
				}
				
			}));
		}
		return convertView;
	}

	class ViewHolder {
		View baseview;
		ImageView imgView;
		TextView song_name;
		TextView artist;
		public ViewHolder(View baseview) {
			this.baseview = baseview;
		}

		public ImageView getImgView() {
			if (imgView == null) {
				imgView = (ImageView) baseview.findViewById(R.id.song_img);
			}
			return imgView;
		}

		public TextView getSong_name() {
			if (song_name == null) {
				song_name = (TextView) baseview.findViewById(R.id.song_name);
			}
			return song_name;
		}
		public TextView getArtist() {
			if (artist == null) {
				artist = (TextView) baseview.findViewById(R.id.artist);
			}
			return artist;
		}
	}
}
