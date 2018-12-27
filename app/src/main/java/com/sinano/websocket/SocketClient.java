package com.sinano.websocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class SocketClient extends WebSocketClient {

    private String TAG = "daixinhong";

    private onCallBack mOnCallBack;

    public interface onCallBack {

        void open(ServerHandshake handshake);

        void message(String message);

        void close(int code, String reason, boolean remote);

        void onError(Exception e);

    }

    public void setOnCallBackListener(onCallBack onCallBackListener) {
        this.mOnCallBack = onCallBackListener;
    }

    public SocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

        if (mOnCallBack != null) {
            mOnCallBack.open(handshakedata);
        }

    }

    @Override
    public void onMessage(String message) {
        if (mOnCallBack != null) {
            mOnCallBack.message(message);
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (mOnCallBack != null) {
            mOnCallBack.close(code, reason, remote);
        }
    }

    @Override
    public void onError(Exception ex) {
        if (mOnCallBack != null) {
            mOnCallBack.onError(ex);
        }
    }
}
