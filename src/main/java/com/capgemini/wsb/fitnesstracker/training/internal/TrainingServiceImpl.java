package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Provide Impl
@Service
public class TrainingServiceImpl implements TrainingProvider {

    private List<Training> trainingList = new ArrayList<>();

    @Override
    public Optional<User> getTraining(Long trainingId) {
        return trainingList.stream()
                .filter(training -> training.getId().equals(trainingId))
                .map(Training::getUser)
                .findFirst();
    }

    @Override
    public void createTraining(Training training) {
        trainingList.add(training);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingList;
    }


    @Override
    public List<Training> getTrainingsForUser(Long userId) {
        List<Training> userTrainings = new ArrayList<>();
        for (Training training : trainingList) {
            if (training.getUser().getId() == userId) {
                userTrainings.add(training);
            }
        }
        return userTrainings;
    }
    @Override
    public List<Training> getCompletedTrainingsAfterDate(String date) {
        Date inputDate = parseDate(date);
        return trainingList.stream()
                .filter(training -> training.getEndTime().after(inputDate))
                .collect(Collectors.toList());
    }

    private Date parseDate(String dateStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // Zakładając format daty "yyyy-MM-dd"
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace(); // Obsługa błędu parsowania daty
            return null;
        }
    }
    @Override
    public List<Training> getTrainingsByActivityType(String activityType) {
        List<Training> userTrainings = new ArrayList<>();
        for (Training training : trainingList) {
            String kk = training.getActivityType().getDisplayName();
            String z = training.getActivityType().name();
            if (kk.equals(activityType) ) {
                userTrainings.add(training);
            }
        }
        return userTrainings;
    }

    @Override
    public void updateTraining(Long trainingId, Training updatedTraining) {
        trainingList.stream()
                .filter(training -> training.getId().equals(trainingId))
                .findFirst()
                .ifPresent(training -> {
                    training.setDistance(updatedTraining.getDistance());
                    // Update other fields as needed
                });
    }


}
