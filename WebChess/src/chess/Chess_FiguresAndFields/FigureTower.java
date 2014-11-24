/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Chess_FiguresAndFields;
import chess.Chess_Board.Board;
/**
 *
 * @author Lucas
 */
public class FigureTower extends Chessfigure
{

    public FigureTower(Field field, String color, Board board)
    {
        super(field, color, board);
    }

int maxMoveCount=8;
public boolean canFigureMakeThisMove(MoveInformations moveinformation)
{
    boolean returnvalue=false;
    if (moveinformation.TYPE!=TYPE_MOVES.DIAGONAL_MOVE && moveinformation.TYPE!=TYPE_MOVES.JUMPER_MOVE && moveinformation.TYPE!=TYPE_MOVES.IMPOSSIBLE_MOVE && moveinformation.COUNT<=maxMoveCount)
    {    
        returnvalue= true;
    }
    return returnvalue;
}

public void actionsAfterMove(Field oldField)
{
    
}

    
}