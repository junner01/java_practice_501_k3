package ex_241022_ch4_2.homework;

public class Cgw0910Class {
	boolean whiteTeam;
	int pieceColumn;
	int pieceRow;
	String canMove;
	String pieceName;
	
	public Cgw0910Class(boolean whiteTeam, int pieceColumn, int pieceRow, String pieceName) {
		this.whiteTeam = whiteTeam;
		if (whiteTeam) {
			this.pieceColumn = pieceColumn;
			this.pieceRow = pieceRow;
		}
		else {
			this.pieceColumn = 8 - (pieceColumn - 1);
			this.pieceRow = pieceRow;
		}
		this.pieceName = pieceName;
	}
}

class Cgw0910Pawn extends Cgw0910Class {
    public Cgw0910Pawn(boolean whiteTeam, int pieceColumn, int pieceRow) {
        super(whiteTeam, pieceColumn, pieceRow, "P");
        canMove = "처음 행동 시 앞으로 두 칸, 기본 앞으로 한 칸, 전방에 다른 기물이 있을 경우 이동불가, 앞 쪽 대각선에 기물이 있을 경우 Hunting가능";
    }
}
class Cgw0910Look extends Cgw0910Class {
    public Cgw0910Look(boolean whiteTeam, int pieceColumn, int pieceRow) {
        super(whiteTeam, pieceColumn, pieceRow, "L");
        canMove = "직선 방향으로 무제한으로 갈 수 있지만 기물이 있을 경우 해당 기물 앞까지만 이동가능, 기물의 team bool이 다를 경우 해당 기물 Hunting가능";
    }
}
class Cgw0910Knight extends Cgw0910Class {
    public Cgw0910Knight(boolean whiteTeam, int pieceColumn, int pieceRow) {
        super(whiteTeam, pieceColumn, pieceRow, "N");
        canMove = "기본적으로 직선방향으로 한칸 + 해당 직선방향 대각선으로 한칸 이동 가능, 이동 지점의 기물의 team bool이 같을 경우 이동불가, 다를 경우 Hunting가능";
    }
}
class Cgw0910Bishop extends Cgw0910Class {
    public Cgw0910Bishop(boolean whiteTeam, int pieceColumn, int pieceRow) {
        super(whiteTeam, pieceColumn, pieceRow, "B");
        canMove = "대각선 방향으로 무제한으로 갈 수 있지만 기물이 있을 경우 해당 기물 앞까지만 이동가능, 기물의 team bool이 다를 경우 해당 기물 Hunting가능";
    }
}
class Cgw0910Queen extends Cgw0910Class {
    public Cgw0910Queen(boolean whiteTeam, int pieceColumn, int pieceRow) {
        super(whiteTeam, pieceColumn, pieceRow, "Q");
        canMove = "대각선과 직선 방향으로 무제한으로 갈 수 있지만 기물이 있을 경우 해당 기물 앞까지만 이동가능, 기물의 team bool이 다를 경우 해당 기물 Hunting가능";
    }
}
class Cgw0910King extends Cgw0910Class {
    public Cgw0910King(boolean whiteTeam, int pieceColumn, int pieceRow) {
        super(whiteTeam, pieceColumn, pieceRow, "K");
        canMove = "대각선과 직선 방향으로 한칸식 이동가능, 이동 지점의 기물의 team bool이 같을 경우 이동불가, 다를 경우 Hunting가능, 이동지점이 다른 team bool 기물에게 Hunting되는 지점일 때 이동불가.";
    }
}