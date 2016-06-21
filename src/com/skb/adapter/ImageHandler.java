//package com.skb.adapter;
//
//import java.lang.ref.WeakReference;
//
//import android.os.Handler;
//import android.os.Message;
//
//import com.skb.ui.HangoutActivity;
//
//public class ImageHandler extends Handler {
//
//	/**
//	 * ���������ʾ��View��
//	 */
//	public static final int MSG_UPDATE_IMAGE = 1;
//	/**
//	 * ������ͣ�ֲ���
//	 */
//	public static final int MSG_KEEP_SILENT = 2;
//	/**
//	 * ����ָ��ֲ���
//	 */
//	public  static final int MSG_BREAK_SILENT = 3;
//	/**
//	 * ��¼���µ�ҳ�ţ����û��ֶ�����ʱ��Ҫ��¼��ҳ�ţ������ʹ�ֲ���ҳ����� ���統ǰ����ڵ�һҳ������׼�����ŵ��ǵڶ�ҳ������ʱ���û���������ĩҳ��
//	 * ��Ӧ�ò��ŵ��ǵ�һҳ�������������ԭ���ĵڶ�ҳ���ţ����߼��������⡣
//	 */
//	public static final int MSG_PAGE_CHANGED = 4;
//
//	// �ֲ����ʱ��
//	public static final long MSG_DELAY = 3000;
//
//	// ʹ�������ã�����handlerй¶
//	private WeakReference<HangoutActivity> weakReference;
//	private int currentItem = 0;
//
//	public ImageHandler(WeakReference<HangoutActivity> wk) {
//		weakReference = wk;
//	}
//
//	@Override
//	public void handleMessage(Message msg) {
//		super.handleMessage(msg);
//		HangoutActivity activity = weakReference.get();
//		if (activity == null) {
//			// activity�Ѿ����գ������ٴ���ui
//			return;
//		}
//		// �����Ϣ���У����Ƴ�δ���͵���Ϣ
//		if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)) {
//			activity.handler.removeMessages(MSG_UPDATE_IMAGE);
//		}
//		switch (msg.what) {
//		case MSG_UPDATE_IMAGE:
//			currentItem++;
//			activity.mViewPager.setCurrentItem(currentItem);
//			activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
//			break;
//		case MSG_KEEP_SILENT:
//			//ֻҪ��������Ϣ����ͣ��
//			break;
//		case MSG_BREAK_SILENT:
//			activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
//			break;
//		case MSG_PAGE_CHANGED:
//			//	��¼��ǰҳ�ţ����ⲥ��ʱ��ʾҳ�治��ȷ
//			currentItem=msg.arg1;
//			break;
//			default:
//			break;
//
//		}
//
//	}
//}
