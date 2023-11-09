package MyFigures;

public class Queen extends Figure
{
    public Queen(String name, char color)
    {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1, Figure[][] board)
    {
        if ((row == row1) || (col == col1))
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

        if (Math.abs(row-row1) == Math.abs(col-col1))
        {
            int stepRow = row<row1 ? 1 : -1;
            int stepCol = col<col1 ? 1 : -1;
            int j = col + stepCol;
            for(int i = row+stepRow; i != row1; i += stepRow)
            {
                if(board[i][j] != null)
                {
                    return false;
                }
                j += stepCol;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1, Figure[][] board)
    {
        if (this.canMove(row, col, row1, col1, board) && board[row1][col1] != null && board[row1][col1].getColor() != this.getColor())
        {
            return true;
        }
        return false;
    }
}