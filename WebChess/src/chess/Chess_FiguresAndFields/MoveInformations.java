/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Chess_FiguresAndFields;

/**
 *
 * @author Lucas
 */
public class MoveInformations {
    public int X;
    public int Y;
    public int TYPE;
    public int COUNT;
    MoveInformations(int MOVETYPE, int moveX, int moveY, int MOVECOUNT)
    {
        X=moveX;
        Y=moveY;
        TYPE=MOVETYPE;
        COUNT=MOVECOUNT;
    }
            
}
