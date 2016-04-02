package ca.warp7.robot.autonomous;

import ca.warp7.robot.autonomous.autoModuals.BasicModules;
import ca.warp7.robot.subsystems.Drive;
import ca.warp7.robot.subsystems.Intake;
import ca.warp7.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;

public class ShootAuto extends AutonomousBase{

	private static int step;
	
	public ShootAuto(Intake intake){
		step = 1;
		intake.toggleInitialArm();
		Timer.delay(0.5);
	}
	
	@Override
	public void periodic(Drive drive, Shooter shooter, Intake intake) {
		switch(step){
		case 1:
			drive.stop();
			shooter.spinUp();
			shooter.setHood(0.3);
			if (shooter.atTargetRPM()) {
                intake.fireBall();
            }
			if(!intake.hasBall()){
				Timer.delay(2);
				step++;
			}
			break;
		case 2:
			intake.stopIntake();
			drive.stop();
			break;
		default:
			intake.stopIntake();
			drive.stop();
			break;
		}
	}

}
