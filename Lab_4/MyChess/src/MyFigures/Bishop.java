package MyFigures;
public class Bishop extends Figure
{
    public Bishop(String name, char color)
    {
        super(name, color);
    }
    @Override
    public boolean canMove(int row, int col, int row1, int col1, Figure[][] board)
    {
        if (Math.abs(row - row1) == Math.abs(col - col1))
        {
            int rowDir = row < row1 ? 1 : -1;
            int colDir = col < col1 ? 1 : -1;
            int tempRow = row + rowDir;
            int tempCol = col + colDir;
            while (tempRow != row1 && tempCol != col1)
            {
                if (board[tempRow][tempCol] != null)
                {
                    return false;
                }
                tempRow += rowDir;
                tempCol += colDir;
            }
        }
        return false;
    }
    @Override
    public boolean canAttack(int row, int col, int row1, int col1, Figure[][] board)
    {
        return this.canMove(row, col, row1, col1, board) &&
                board[row1][col1] != null &&
                board[row1][col1].getColor() != this.getColor();
    }
}