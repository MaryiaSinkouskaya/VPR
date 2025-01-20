package com.vpr.app.dto.request;

import com.vpr.app.enums.LaborOutcome;
import com.vpr.app.enums.Ploidity;
import lombok.Data;

import java.util.Date;

@Data
public class ProbandRequestDto {

    private Date birthDate;

    private String karyotype;

    private int pregnancyDurationInWeeks;

    private double weight;

    private double head;

    private int pregnancyNumber;

    private boolean isAborted;

    private String gender;

    private Ploidity ploid;

    private LaborOutcome laborOutcome;

    private NoteRequestDto note;

    private PersonInfoRequestDto father;

    private OrganizationRequestDto organization;

    private MotherRequestDto mother;

    private AbnormalityRequestDto abnormality;

}
