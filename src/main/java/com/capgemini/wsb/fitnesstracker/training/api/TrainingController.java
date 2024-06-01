package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings")
public class TrainingController {
    @Autowired
    private  TrainingProvider trainingProvider;
    @Autowired
    private UserService userService; // Załóżmy, że istnieje serwis UserService
    @PostMapping
    public ResponseEntity<?> createTraining(@RequestBody Training training) {
        if (training == null) {
            return ResponseEntity.badRequest().body("Training data is required");
        }
        try {
            trainingProvider.createTraining(training);
            return ResponseEntity.ok().body("Training created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not create training");
        }
    }
    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingProvider.getAllTrainings();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getTrainingsForUser(@PathVariable Long userId) {
        List<Training> userTrainings = trainingProvider.getTrainingsForUser(userId);
        if (userTrainings != null) {
            return ResponseEntity.ok(userTrainings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/completed")
    public List<Training> getCompletedTrainingsAfterDate(@RequestParam("date") String date) {
        return trainingProvider.getCompletedTrainingsAfterDate(date);
    }

    @GetMapping("/activity/{activityType}")
    public List<Training> getTrainingsByActivityType(@PathVariable String activityType) {
        return trainingProvider.getTrainingsByActivityType(activityType);
    }

    @PutMapping("/{trainingId}")
    public ResponseEntity<?> updateTraining(@PathVariable Long trainingId, @RequestBody Training updatedTraining) {
        trainingProvider.updateTraining(trainingId, updatedTraining);
        return ResponseEntity.ok().build();
    }
    // Możesz dodać inne metody obsługujące różne operacje na treningach
}