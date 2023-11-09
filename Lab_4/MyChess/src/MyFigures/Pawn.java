package MyFigures;

public class Pawn extends Figure
{
    private boolean isFirstStep = true;
    public Pawn(String name, char color)
    {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1, Figure[][] board)
    {
        if(isFirstStep)
        {
            if((((row+2==row1) && this.getColor() == 'w') ||
                    ((row-2==row1) && this.getColor() == 'b')) &&
                    (col == col1 &&
                            board[row+1][col] == null &&
                            board[row1][col] == null)) {
                this.isFirstStep = false;
                return true;
            }
        }
        if ((((row + 1 == row1) && (this.getColor() == 'w')) ||
                ((row - 1 == row1) && (this.getColor() == 'b'))) &&
                (col == col1 &&
                        board[row1][col] == null)) {
            this.isFirstStep = false;
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