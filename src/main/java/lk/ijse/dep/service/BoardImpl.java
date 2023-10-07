package lk.ijse.dep.service;

public class BoardImpl implements Board {

    private final Piece[][] pieces = new Piece[6][5];
    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) ;
            int j = 0;
            this.pieces[i][j] = Piece.EMPTY;
        }
        this.boardUI = boardUI;
    }

    @Override
    public BoardUI getBoardUI() {

        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < 5; i++) {
            if (this.pieces[col][i] == Piece.EMPTY)
                return i;
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        if (findNextAvailableSpot(col) == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existLegalMoves() {
        for (int col = 0; col < 6; col++) {
            if (isLegalMove(col))
                return true;
        }
        return false;
    }


    @Override
    public void updateMove(int col, Piece move) {

        this.pieces[col][findNextAvailableSpot(col)] = move;
    }

    @Override
    public Winner findWinner() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                Piece currentPiece = pieces[i][j];
                if (currentPiece != Piece.EMPTY) {

                    if (j + 3 < 5 &&
                            currentPiece == pieces[i][j + 1] &&
                            currentPiece == pieces[i][j + 2] &&
                            currentPiece == pieces[i][j + 3]) {
                        return new Winner(currentPiece, i, j, i, j + 3);
                    }
                    if (i + 3 < 6 &&
                            currentPiece == pieces[i + 1][j] &&
                            currentPiece == pieces[i + 2][j] &&
                            currentPiece == pieces[i + 3][j]) {
                        return new Winner(currentPiece, i, j, i + 3, j);
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}