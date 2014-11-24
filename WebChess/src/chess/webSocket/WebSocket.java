package chess.webSocket;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Christian.Senk
 * @date 25.08.2010
 * @time 12:04:46
 *
 * Represents the GWT version of the JavaScripts new WebSocket component.
 */
@SuppressWarnings("unused")
public class WebSocket {
       
        /**
         * @return <code>True</code> if the WebSocket component is supported by the current browser
         */
        public static native boolean IsSupported() /*-{
                if ($wnd.WebSocket) {
                        return true;
                } else {
                        return false;
                }
        }-*/;
       
        private final JavaScriptObject jsWebSocket;
        private final WebSocketCallback socketCallback;
       
        /**
         * Creates a new {@link WebSocket} that connects immediately to the end-point URL.
         *
         * @param url
         * @param socketCallback
         */
        public WebSocket(final String url, final WebSocketCallback socketCallback) {
                assert url != null;
                assert socketCallback != null;
                assert IsSupported();
               
                this.socketCallback = socketCallback;
                this.jsWebSocket = createJSWebSocket(url, this);
        }
       
        /**
         * Sends a message.
         *
         * @param message
         */
        public native void send(String message) /*-{
                if (message == null)
                        return;
               
                this.@chess.webSocket.WebSocket::jsWebSocket.send(message);
        }-*/;
       
        /**
         * Closes this {@link WebSocket}.
         */
        public native void close() /*-{
                this.@chess.webSocket.WebSocket::jsWebSocket.close();
        }-*/;
       
        /**
         * @return the bufferedAmount property of the underlying JavaScript WebSocket.
         */
        public native int getBufferedAmount() /*-{
                return this.@chess.webSocket.WebSocket::jsWebSocket.bufferedAmount;
        }-*/;
       
        /**
         * @return the readyState property of the underlying JavaScript WebSocket.
         */
        public native int getReadyState() /*-{
                return this.@chess.webSocket.WebSocket::jsWebSocket.readyState;
        }-*/;
       
        /**
         * @return the url property of the underlying JavaScript WebSocket.
         */
        public native String getURL() /*-{
                return this.@chess.webSocket.WebSocket::jsWebSocket.url;
        }-*/;
       
        /**
         * Creates the JavaScript WebSocket component and set's all callback handlers.
         *
         * @param url
         */
        private native JavaScriptObject createJSWebSocket(final String url, final WebSocket webSocket) /*-{
                var jsWebSocket = new WebSocket(url);
               
                jsWebSocket.onopen = function() {
                        webSocket.@chess.webSocket.WebSocket::onOpen()();
                }
               
                jsWebSocket.onclose = function() {
                        webSocket.@chess.webSocket.WebSocket::onClose()();
                }
               
                jsWebSocket.onerror = function() {
                        webSocket.@chess.webSocket.WebSocket::onError()();
                }
               
                jsWebSocket.onmessage = function(socketResponse) {
                        if (socketResponse.data) {
                                webSocket.@chess.webSocket.WebSocket::onMessage(Ljava/lang/String;)(socketResponse.data);
                        }
                }
               
                return jsWebSocket;
        }-*/;
       
        /**
         *
         */
        private void onOpen() {
                socketCallback.onOpen(this);
        }
       
        /**
         * @param message
         */
        private void onMessage(String message) {
                socketCallback.onMessage(this, message);
        }
       
        /**
         *
         */
        private void onError() {
                socketCallback.onError(this);
        }
       
        /**
         *
         */
        private void onClose() {
                socketCallback.onClose(this);
        }
       
}

