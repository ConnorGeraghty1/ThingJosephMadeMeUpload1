package frc.robot.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Intake;

public class IntakeCommand extends Command {
    private Arm arm;
    private Intake intake;
    private boolean isFinished = false;
    public int elapsed = 0;
    public boolean triggered = false;
    public IntakeCommand(){
        addRequirements(Constants.intake);

    }
    @Override
    public void initialize(){
        intake = Constants.intake;
        triggered = false;
        elapsed = 0;
    }   

    @Override
    public void execute(){
        intake.runSlow();

        if(Constants.intake.intakeSensor.getVoltage()<.5) {
            triggered = true;
        }
        if(triggered == true) {
            elapsed++;
        }
        if(triggered) {
            isFinished = true;
            elapsed = 0;
            Constants.hasNote = true;
        }
    }
    @Override
    public boolean isFinished(){
         
        return false;

    }

    @Override
    public void end(boolean interrupted) {
        Constants.intake.stop();
        isFinished = true;
    }
}
