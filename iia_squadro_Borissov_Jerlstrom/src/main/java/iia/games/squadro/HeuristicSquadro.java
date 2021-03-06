package iia.games.squadro;

import iia.games.base.IHeuristic;

public class HeuristicSquadro {
	public static final IHeuristic<RoleSquadro, BoardSquadro> hZero = (BoardSquadro, r) -> 0;

	public static final IHeuristic<RoleSquadro, BoardSquadro> hReal = (BoardSquadro, r) -> {
		int scoreAmi = 0;
		int scoreAdv = 0;
		int nbAdvFini = 0;
		int nbAmiFini = 0;
		int nbAmiAl = 0;
		int oldX = -1;
		int nbAdvRetour = 0;
		int nbAmiRetour = 0;
		
		BoardSquadro bCop = BoardSquadro.copy();
		switch(r) {
		case VERTICAL:
			
			for(int i = 0; i < 10; i++) {
				if(i <=4) {//ADV
					if(bCop.Board.get(i).y==1) {
						scoreAdv += 6 + (6 - bCop.Board.get(i).x);
						if(bCop.Board.get(i).x == 0) {
							scoreAdv += 12;
							nbAdvFini++;
						} else {
							nbAdvRetour++;
						}
						
					}else {
						scoreAdv += bCop.Board.get(i).x;
					}
				} else {//AMI
					if(bCop.Board.get(i).y==1) {
						scoreAmi += 6 + (6 - bCop.Board.get(i).x);
						if(bCop.Board.get(i).x == 0) {
							scoreAmi += 12;
							nbAmiFini++;
						} else {
							nbAmiRetour++;
						}
						if(i > 5 && bCop.Board.get(i).x != 6) {
							if(bCop.Board.get(i).x == oldX) nbAmiAl++;
						}
						oldX =  bCop.Board.get(i).x;
						
					}else {
						scoreAmi += bCop.Board.get(i).x;
						
						if(i > 5 && bCop.Board.get(i).x != 0) {
							if(bCop.Board.get(i).x == oldX) nbAmiAl++;
						}
						oldX =  bCop.Board.get(i).x;
					}
				}
			}
			
			break;
		case HORIZONTAL:
			
			for(int i = 0; i < 10; i++) {
				if(i <=4) {//AMI
					if(bCop.Board.get(i).y==1) {
						scoreAmi += 6 + (6 - bCop.Board.get(i).x);
						if(bCop.Board.get(i).x == 0) {
							scoreAmi += 12;
							nbAmiFini++;
						}  else {
							nbAmiRetour++;
						}
						
						if(i > 5 && bCop.Board.get(i).x != 6) {
							if(bCop.Board.get(i).x == oldX) nbAmiAl++;
						}
						oldX =  bCop.Board.get(i).x;
					}else {
						scoreAmi += bCop.Board.get(i).x;
						

						if(i > 5 && bCop.Board.get(i).x != 0) {
							if(bCop.Board.get(i).x == oldX) nbAmiAl++;
						}
						oldX =  bCop.Board.get(i).x;
					}
				} else {//ADV
					if(bCop.Board.get(i).y==1) {
						scoreAdv += 6 + (6 - bCop.Board.get(i).x);
						if(bCop.Board.get(i).x == 0) {
							scoreAdv += 12;
							nbAdvFini++;
						} else {
							nbAdvRetour++;
						}
							
						
					
					}else {
						scoreAdv += bCop.Board.get(i).x;
						
						
					}
				}
			}
			
			break;
		}
	

		
		if(nbAmiFini == 4) {
			scoreAmi += 40;
		}
		if(nbAdvFini == 4) {
			scoreAdv += 3 * 40;
		}
		
	

		int heuristicValue =  scoreAmi - scoreAdv - (10*nbAmiAl) +  (nbAmiRetour - (2*nbAdvRetour));
		return heuristicValue;
	};
}
