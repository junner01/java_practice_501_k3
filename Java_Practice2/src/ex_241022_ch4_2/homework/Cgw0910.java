package ex_241022_ch4_2.homework;
import java.util.Random;

public class Cgw0910 {
	Cgw0910Class[][] pieceCool = new Cgw0910Class[8][8];

	public Cgw0910() {
		Cgw0910Class[] piecesArr = new Cgw0910Class[32];
		int arrayNum = 0;
		boolean whiteTeam = true;

		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 9; j++) {
				piecesArr[arrayNum] = new Cgw0910Pawn(whiteTeam, 2, j);
				arrayNum++;
			}
			piecesArr[arrayNum] = new Cgw0910Look(whiteTeam, 1, 1);
			arrayNum++;
			piecesArr[arrayNum] = new Cgw0910Knight(whiteTeam, 1, 2);
			arrayNum++;
			piecesArr[arrayNum] = new Cgw0910Bishop(whiteTeam, 1, 3);
			arrayNum++;
			piecesArr[arrayNum] = new Cgw0910Queen(whiteTeam, 1, 4);
			arrayNum++;
			piecesArr[arrayNum] = new Cgw0910King(whiteTeam, 1, 5);
			arrayNum++;
			piecesArr[arrayNum] = new Cgw0910Bishop(whiteTeam, 1, 6);
			arrayNum++;
			piecesArr[arrayNum] = new Cgw0910Knight(whiteTeam, 1, 7);
			arrayNum++;
			piecesArr[arrayNum] = new Cgw0910Look(whiteTeam, 1, 8);
			arrayNum++;
			whiteTeam = false;
		}
		for (Cgw0910Class piece: piecesArr) {
			pieceCool[piece.pieceColumn - 1][piece.pieceRow - 1] = piece;
		}
	}
	public static void main(String[] args) {
		Random random = new Random();
		Cgw0910 chessMaker = new Cgw0910();
		for (Cgw0910Class[] pieces: chessMaker.pieceCool) {
			for (Cgw0910Class piece: pieces) {
				if (piece == null) {
					System.out.print("[" + " ]");
				}
				else {
					System.out.print("[" + piece.pieceName + "]");
				}
			}
			System.out.println();
		}
		Cgw0910Class pawn = chessMaker.pieceCool[1][1];
		Cgw0910Class look = chessMaker.pieceCool[0][0];
		Cgw0910Class knight = chessMaker.pieceCool[0][1];
		Cgw0910Class bishop = chessMaker.pieceCool[0][2];
		Cgw0910Class queen = chessMaker.pieceCool[0][3];
		Cgw0910Class king = chessMaker.pieceCool[0][4];
		Cgw0910Class[] randomPieceIntroduce = new Cgw0910Class[] {pawn, look, knight, bishop, queen, king};
		Cgw0910Class introducePiece = randomPieceIntroduce[random.nextInt(randomPieceIntroduce.length)];
		System.out.println(introducePiece.pieceName + ": " + introducePiece.canMove);
		
	}
}
