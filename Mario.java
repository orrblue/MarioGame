
public class Mario extends MovingImage {
	// FIELDS
		private boolean isFalling;
		private boolean isWalkingLeft;
		private boolean isWalkingRight;
		//private int jumpTimer;
		private int yVel;
		private int xVel;

	// CONSTRUCTOR
	public Mario(int x, int y) {
		super("mario.png",x,y,40,50);
		isFalling = true;
		isWalkingLeft = false;
		isWalkingRight = false;


	}

	// METHODS

	public void jump(){

		if(!isFalling){
			yVel = -20;
			moveByAmount(xVel, yVel);
		}


	}

	public void walkRight(boolean value){
		isWalkingRight = value;

	}

	public void walkLeft(boolean value){
		isWalkingLeft = value;

	}

	public void fall(boolean value){
		isFalling = value;
		yVel++;

	}

	public boolean testPlatformHit(MovingImage platform){

		int marioFeetX = getX() + getWidth()/2;
		int marioFeetY = getY() + getHeight();

		return platform.isPointInImage(marioFeetX, marioFeetY);
	}

	public void act(){
		//MOVE MARIO




		if(isFalling){
			moveByAmount(xVel,yVel);

		}

		if(isWalkingLeft){
			moveByAmount(-5, 0);
		}

		if(isWalkingRight){
			moveByAmount(5,0);
		}
	}

}