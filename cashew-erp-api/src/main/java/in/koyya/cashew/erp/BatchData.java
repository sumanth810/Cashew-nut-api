package in.koyya.cashew.erp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BatchData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @DecimalMin(value = "0.0", inclusive = false, message = "Today's intake must be positive")
    private double todayIntake;

    @DecimalMin(value = "0.0", inclusive = false, message = "Shelling intake must be positive")
    private double shellingIntake;

    @DecimalMin(value = "0.0", inclusive = false, message = "Peeling intake must be positive")
    private double peelingIntake;

    @DecimalMin(value = "0.0", inclusive = false, message = "Grading intake must be positive")
    private double gradingIntake;

    @DecimalMin(value = "0.0", inclusive = false, message = "Today's output must be positive")
    private double todayOutput;

    @DecimalMin(value = "0.0", inclusive = false, message = "Shelling output must be positive")
    private double shellingOutput;

    @DecimalMin(value = "0.0", inclusive = false, message = "Peeling output must be positive")
    private double peelingOutput;

    @DecimalMin(value = "0.0", inclusive = false, message = "White wholes output must be positive")
    private double whiteWholesOutput;

    @DecimalMin(value = "0.0", inclusive = false, message = "Scorched wholes output must be positive")
    private double scorchedWholesOutput;

    @DecimalMin(value = "0.0", inclusive = false, message = "Cashew forms output must be positive")
    private double cashewFormsOutput;

	public BatchData(Long id, @NotNull(message = "Date cannot be null") LocalDate date,
			@DecimalMin(value = "0.0", inclusive = false, message = "Today's intake must be positive") double todayIntake,
			@DecimalMin(value = "0.0", inclusive = false, message = "Shelling intake must be positive") double shellingIntake,
			@DecimalMin(value = "0.0", inclusive = false, message = "Peeling intake must be positive") double peelingIntake,
			@DecimalMin(value = "0.0", inclusive = false, message = "Grading intake must be positive") double gradingIntake,
			@DecimalMin(value = "0.0", inclusive = false, message = "Today's output must be positive") double todayOutput,
			@DecimalMin(value = "0.0", inclusive = false, message = "Shelling output must be positive") double shellingOutput,
			@DecimalMin(value = "0.0", inclusive = false, message = "Peeling output must be positive") double peelingOutput,
			@DecimalMin(value = "0.0", inclusive = false, message = "White wholes output must be positive") double whiteWholesOutput,
			@DecimalMin(value = "0.0", inclusive = false, message = "Scorched wholes output must be positive") double scorchedWholesOutput,
			@DecimalMin(value = "0.0", inclusive = false, message = "Cashew forms output must be positive") double cashewFormsOutput) {
		super();
		this.id = id;
		this.date = date;
		this.todayIntake = todayIntake;
		this.shellingIntake = shellingIntake;
		this.peelingIntake = peelingIntake;
		this.gradingIntake = gradingIntake;
		this.todayOutput = todayOutput;
		this.shellingOutput = shellingOutput;
		this.peelingOutput = peelingOutput;
		this.whiteWholesOutput = whiteWholesOutput;
		this.scorchedWholesOutput = scorchedWholesOutput;
		this.cashewFormsOutput = cashewFormsOutput;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
