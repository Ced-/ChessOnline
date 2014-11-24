/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Chess_Board;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import chess.Chess_FiguresAndFields.*;
import chess.webSocket.WebSocket;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;


/**
 *
 * @author Lucas
 */
public class Board extends com.google.gwt.user.client.ui.AbsolutePanel{
   /* public Board clone() throws CloneNotSupportedException 
    {  
        return (Board)super.clone();  
    }  */
    
    private String whosTurn=TYPE_COLORS.WHITE_COLOR;
    private Chessfigure activeFigure=null;
    public chess.webSocket.WebSocket wsClient;
    private String myColor="";
            
            
            
    public final static int BOARDWIDTH=640;
    private final int FIELDWIDTH=BOARDWIDTH/8;
   
    private chess.Chess_FiguresAndFields.Field[][] Fields =new Field[8][8];
 

    public Board(String playID) {
        initComponents();
       this.setHeight("1000px");
        this.setWidth("1000px");
        
       // this.setBounds(0, 0, BOARDWIDTH+40, BOARDWIDTH+40);
       
        int colorcounter=-1;
        for (int y=0; y<Fields.length; y++)
        {
            colorcounter++;
            for (int x=0; x<Fields[y].length; x++)
            {
                String color=(colorcounter%2==0)? TYPE_COLORS.WHITE_COLOR:TYPE_COLORS.BLACK_COLOR;
                Fields[x][y]=new Field(null,x,y,color,FIELDWIDTH,this);
                Fields[x][y].addClickHandler(new com.google.gwt.event.dom.client.ClickHandler()
                {

                    public void onClick(com.google.gwt.event.dom.client.ClickEvent evt) 
                    {
                       
                       Chessfigure tempfigure=((Field)evt.getSource()).getFigure();
                                    if (tempfigure==activeFigure)
                                    {
                                        //System.out.println(2221);
                                        activeFigure=null;
                                        ((Field)evt.getSource()).setColor("default");
                                    }
                                    else if (tempfigure!=null && activeFigure==null)
                                    {
                                       
                                        if (tempfigure.getColor().equalsIgnoreCase(whosTurn) && myColor.equalsIgnoreCase(whosTurn))
                                        {
                                            activeFigure=tempfigure;
                                            ((Field)evt.getSource()).setColor(TYPE_COLORS.BLUE_COLOR);
                                        }
                                    }
                                    
                                    if(activeFigure!=null && activeFigure!=tempfigure)
                                    {
                                        if (activeFigure.manualMove((Field)evt.getSource()))
                                        {
                                          //  System.out.println("test1");
                                            nextTurn();
                                            
                                        }
                                    }

                                    
                    
                          
                          }
                    
                });
                Fields[x][y].setSize(Integer.toString(FIELDWIDTH) + "px", Integer.toString(FIELDWIDTH) + "px");
                this.add(Fields[x][y], x*FIELDWIDTH, y*FIELDWIDTH);
          
                
                
                colorcounter++;
            }
        }
        makeFirstFigures();
        initializeWebsocket(playID);
        
    }
    
    
    
    
    private void initializeWebsocket(String playID) 
    {
    	final String pid=playID;
        wsClient=new chess.webSocket.WebSocket("wss://webchess-chessserver.nodejitsu.com:443", new chess.webSocket.WebSocketCallback()
    	 //wsClient=new chess.webSocket.WebSocket("ws://localhost:443", new chess.webSocket.WebSocketCallback()
    	{

			@Override
			public void onOpen(WebSocket webSocket) {
				webSocket.send(pid);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClose(WebSocket webSocket) {
				// TODO Auto-generated method stub
				Window.Location.assign("index.html?err=1");
				
			}

			@Override
			public void onMessage(WebSocket webSocket, String message) {
				String[] coords=message.split("@@@");
				if (coords.length==1)
				{
					String[] startConf=coords[0].split("%%%");
					Board.this.myColor=startConf[0];
					initializeVideo(startConf[1], startConf[0]);
					setupGame(((Board.this.myColor=="white") ? "weiß" : "schwarz"));
				}
				
				else
				{
					Field requestedField=Board.this.getField(Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
					//this.board
					requestedField.getFigure().moveToAField(Board.this.getField(Integer.parseInt(coords[2]),Integer.parseInt(coords[3])));
					Board.this.nextTurn();
					
				}
			}

			
			@Override
			public void onError(WebSocket webSocket) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
		
	}

	private native void initializeVideo(String vID, String colorFlag) /*-{
    $wnd.videoChat(vID,colorFlag);
	}-*/;

	private native void setupGame(String infoColor) /*-{
    $wnd.showInformation(infoColor);
	}-*/;

	private void nextTurn()
    {
        whosTurn=(whosTurn.equals(TYPE_COLORS.WHITE_COLOR)) ? TYPE_COLORS.BLACK_COLOR:TYPE_COLORS.WHITE_COLOR;
        activeFigure=null;
        setupGame(((whosTurn=="white") ? "weiß" : "schwarz"));
    }
    
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        //setLayout(new java.awt.GridLayout(8, 8));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    
    private void makeFirstFigures()
    {
    	Chessfigure figure;
        figure=new FigureTower(Fields[0][0],TYPE_COLORS.BLACK_COLOR, this);
        figure=new FigureTower(Fields[7][0],TYPE_COLORS.BLACK_COLOR, this);
        figure=new FigureJumper(Fields[1][0],TYPE_COLORS.BLACK_COLOR, this);
        figure=new FigureJumper(Fields[6][0],TYPE_COLORS.BLACK_COLOR, this);
        figure=new FigureRunner(Fields[2][0],TYPE_COLORS.BLACK_COLOR, this);
        figure=new FigureRunner(Fields[5][0],TYPE_COLORS.BLACK_COLOR, this);
        figure=new FigureQueen(Fields[3][0],TYPE_COLORS.BLACK_COLOR, this);
        figure=new FigureKing(Fields[4][0],TYPE_COLORS.BLACK_COLOR, this);
        
        figure=new FigureTower(Fields[0][7],TYPE_COLORS.WHITE_COLOR, this);
        figure=new FigureTower(Fields[7][7],TYPE_COLORS.WHITE_COLOR, this);
        figure=new FigureJumper(Fields[1][7],TYPE_COLORS.WHITE_COLOR, this);
        figure=new FigureJumper(Fields[6][7],TYPE_COLORS.WHITE_COLOR, this);
        figure=new FigureRunner(Fields[2][7],TYPE_COLORS.WHITE_COLOR, this);
        figure=new FigureRunner(Fields[5][7],TYPE_COLORS.WHITE_COLOR, this);
        figure=new FigureQueen(Fields[3][7],TYPE_COLORS.WHITE_COLOR, this);
        figure=new FigureKing(Fields[4][7],TYPE_COLORS.WHITE_COLOR, this);
        
        for (int i=0; i<8; i++)
        {
            figure=new FigurePawn(Fields[i][1],TYPE_COLORS.BLACK_COLOR, this);
        }
        for (int i=0; i<8; i++)
        {
            figure=new FigurePawn(Fields[i][6],TYPE_COLORS.WHITE_COLOR, this);
        }
        
    }
         
    
    public Field getField(int x, int y)
    {
        if (x>Fields.length-1||y>Fields[x].length-1)
        {
            return null;
        }
        else
        {
            return Fields[x][y];
        }
    }

	

}
