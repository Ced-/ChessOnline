/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Chess_FiguresAndFields;
import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.*;

import chess.Chess_Board.Board;
import chess.webSocket.WebSocket;

/**
 *
 * @author Lucas
 */

public final class Field extends com.google.gwt.user.client.ui.Label{

    

private final int myX;
private final int myY;
private Chessfigure myFigure;
private final String myColor;
private final int myWidth;
private final Board myBoard;
   public Field(Chessfigure figure, int x, int y, String color, int width, Board board)
   {
       myColor=color;
       setColor(myColor);
       myX=x;
       myY=y;
       setFigure(figure);
       myWidth=width;
       myBoard=board;
   }
   
   
   public void setColor(String color)
   {
       
       color = (color.equals(TYPE_COLORS.WHITE_COLOR) || color.equals(TYPE_COLORS.BLACK_COLOR) || color.equals(TYPE_COLORS.BLUE_COLOR))? color:myColor;
       //System.out.println(color);
       if (color.equals(TYPE_COLORS.WHITE_COLOR))
        {
    	   this.getElement().getStyle().setBackgroundColor("#ffffff");
    	  // com.google.gwt.logging.client.ConsoleLogHandler log=new com.google.gwt.logging.client.ConsoleLogHandler();
    	   //java.util.logging.LogRecord rec=new java.util.logging.LogRecord(java.util.logging.Level.ALL, "TEST");
    	  // log.publish(rec);
    	  // log.flush();
        }
       else if (color.equals(TYPE_COLORS.BLACK_COLOR))
        {
    	   this.getElement().getStyle().setBackgroundColor("#000000");
        }
       else if (color.equals(TYPE_COLORS.BLUE_COLOR))
        {
           
    	   this.getElement().getStyle().setBackgroundColor("#0000ff");
        }
       else
        {
    	   this.getElement().getStyle().setBackgroundColor("gray");
        }
       //repaint();
    }
   
   
   void setFigure(Chessfigure figure)
   {
	   this.getElement().removeAllChildren();
       //ImageIcon II=new ImageIcon();
	   com.google.gwt.user.client.ui.Image II = new com.google.gwt.user.client.ui.Image();
       myFigure=figure;
       if (myFigure!=null)
       {
    	    /*org.eclipse.jetty.websocket.WebSocketClientFactory wsClientFactory= new org.eclipse.jetty.websocket.WebSocketClientFactory();
    	    wsClientFactory.start();
    	    org.eclipse.jetty.websocket.WebSocketClient wsClient = wsClientFactory.newWebSocketClient();
    	   
    	   try 
    	    {
	    	    
    	    	java.net.URI webSocketURI=new java.net.URI("ws://wsurl");
	    	    wsClient.open(webSocketURI, new org.eclipse.jetty.websocket.WebSocket()
		    	    {
		
						@Override
						public void onClose(int arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
		
						@Override
						public void onOpen(Connection arg0) 
						{
							// TODO Auto-generated method stub
							
						}
		    	    	
		    	    });
	    	   
    	    }
    	    catch (Exception e){};*/
    	    
    	    II = new com.google.gwt.user.client.ui.Image(myFigure.getIcon());
    	    II.setHeight(Integer.toString(this.myWidth) + "px");
    	    II.setWidth(Integer.toString(this.myWidth) + "px");
            //II=new ImageIcon(myFigure.getIcon());
            //ystem.out.println(myFigure.getIcon());
    	    this.getElement().appendChild(II.getElement());
       }
       
       
       setColor(myColor);//and repaint
       //repaint();    
   }
   
   public Chessfigure getFigure()
   {
       return myFigure;
   }
   
   
   final int getChessX()
   {
       return myX;
   }
   
   final int getChessY()
   {
       return myY;
   }
   
   /*
   public void makeMove(Field oldField)
   {
       Figure figure;
       if (oldField=null)
       {
           
       }
               
       this.myFigure=oldField.getFigure();
   
   public void isMovePossible(Field oldField)
   {
       
   }
   }
   /*{
       if (oldfield==null)
       {
           setNewFigure(null, myX, myY);
       }
       else
       {
           setNewFigure(oldfield.getFigure(), myX, myY);
       }
   }
   
  private Chessfigure setNewFigure(Chessfigure figure, int x, int y)
   {
       
     myFigure=figure;
       if oldfield!=null
               {
                   oldfigure=oldfield.getFigure();
               }
               
       switch figure.isMovePossible(x, y)
       {
               case 1: //possible - do the move and set oldfield to null
                   myFigure=figure;
                   figure.moveFigure(x,y);
                   
               case 2:
                   
       }
     
       if (figure.isMovePossible(figure, x, y))
       {
           oldfield.
                   doTheMove=true;
       }
       else 
       {
           
       }
   }
   
   public Chessfigure getFigure()
   {
       return myFigure;
   }

   this.

    @Override
    public void setIcon(Icon defaultIcon) {
        super.setIcon(defaultIcon);
    }
    

    @Override
    public void repaint() {
        super.repaint();
    }
   JButton Fieldbutton= new JButton("ee","ee")
   */
   
    
}
