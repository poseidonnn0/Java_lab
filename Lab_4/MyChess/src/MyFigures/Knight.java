package MyFigures;
public class Knight extends Figure
{
    public Knight(String name, char color)
    {
        super(name, color);
    }
    @Override
    public boolean canMove(int row, int col, int row1, int col1, Figure[][] board)
    {
        return ((Math.abs(row - row1) == 2 && Math.abs(col - col1) == 1) ||
                (Math.abs(row - row1) == 1 && Math.abs(col - col1) == 2)) &&
                (row1 >= 0 && row1 < 8) && (col1 >= 0 && col1 < 8) &&
                (row != row1 || col != col1);
    }
    @Override
    public boolean canAttack(int row, int col, int row1, int col1, Figure[][] board)
    {
        return this.canMove(row, col, row1, col1, board) &&
                board[row1][col1] != null &&
                board[row1][col1].getColor() != this.getColor();
    }
}