package lk.ijse.dep.service;

public class HumanPlayer extends Player{

    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        if (board.isLegalMove(col)) {
            board.updateMove( col, Piece.BLUE);
            board.getBoardUI().update(col, true);
            Winner w=board.findWinner();

            if(w.getWinningPiece()!=Piece.EMPTY){
                board.getBoardUI().notifyWinner(w);
            }else{
                board.existLegalMoves();
            }

        }
    }
}
