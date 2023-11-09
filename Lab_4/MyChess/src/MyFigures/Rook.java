package MyFigures;

public class Rook extends Figure
{
    public Rook(String name, char color)
    {
        super(name, color);
    }
    @Override
    public boolean canMove(int row, int col, int row1, int col1, Figure[][] board)
    {
        if (row == row1)
        {
            int step = col < col1 ? 1 : -1;
            for(int i = col+step; i != col1; i += step)
            {
                if(board[row][i] != null)
                {
                    return false;
                }
            }
            return true;
        }
        if (col == col1)
        {
            int step = row < row1 ? 1 : -1;
            for(int i = row+step; i != row1; i += step)
            {
                if(board[i][col] != null)
                {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
    @Override
    public boolean canAttack(int row, int col, int row1, int col1, Figure[][] board)
    {
        if((Math.abs(row - row1) == 1 && Math.abs(col - col1) == 1) &&
                board[row1][col1] != null &&
                board[row1][col1].getColor() != this.getColor())
        {
            return true;
        }
        return false;
    }
}