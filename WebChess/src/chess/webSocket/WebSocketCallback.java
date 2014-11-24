package chess.webSocket;

/**
 * @author Christian.Senk
 * @date 25.08.2010
 * @time 12:08:28
 *
 * Interface definition to create a call-back for a {@link WebSocket}.
 */
public interface WebSocketCallback {

        //WebSocketCallback(Board board);

		/**
         * Called if the {@link WebSocket} has opened.
         *
         * @param webSocket
         */
        void onOpen(WebSocket webSocket);
       
        /**
         * Called if the {@link WebSocket} was closed.
         *
         * @param webSocket
         */
        void onClose(WebSocket webSocket);
       
        /**
         * Called if a message arrived in the {@link WebSocket}.
         *
         * @param webSocket
         * @param message the delivered message.
         */
        void onMessage(WebSocket webSocket, String message);
       
        /**
         * Called if an error occurred during the usage of the {@link WebSocket}
         *
         * @param webSocket
         */
        void onError(WebSocket webSocket);
       
}
