package com.minuser.MinUser.seeder;

import com.minuser.MinUser.entities.JobEntity;
import com.minuser.MinUser.entities.UserEntity;
import com.minuser.MinUser.entities.WorkAreaEntity;
import com.minuser.MinUser.repositories.JobRepository;
import com.minuser.MinUser.repositories.UserRepository;
import com.minuser.MinUser.repositories.WorkAreaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DatabaseSeeder
 */
@Configuration
public class DatabaseSeeder
{
    private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

    private WorkAreaRepository workAreaRepository;

    private JobRepository jobRepository;

    private UserRepository userRepository;

    public DatabaseSeeder(WorkAreaRepository workAreaRepository, JobRepository jobRepository, UserRepository userRepository)
    {
        this.workAreaRepository = workAreaRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public void clearSeeder()
    {
        this.userRepository.deleteAll();
        this.jobRepository.deleteAll();
        this.workAreaRepository.deleteAll();
    }

    @Bean
    public CommandLineRunner workAreaSeeder()
    {
        return (args) -> {
            this.workAreaRepository.save(new WorkAreaEntity("Sistemas e Informática"));
            this.workAreaRepository.save(new WorkAreaEntity("RRHH"));
            log.info("Áreas de trabajo...");
            log.info("---------------------------");

            for (WorkAreaEntity workAreaEntity : this.workAreaRepository.findAll()) {
                log.info(workAreaEntity.toString());
            }
        };
    }

    @Bean
    public CommandLineRunner jobSeeder()
    {
        return (args) -> {
            for (WorkAreaEntity workAreaEntity : this.workAreaRepository.findAll()) {
                this.jobRepository.save(new JobEntity("Backend for " + workAreaEntity.getName(), workAreaEntity));
            }

            log.info("Puestos de trabajo...");
            log.info("---------------------------");

            for (JobEntity jobEntity : this.jobRepository.findAll()) {
                log.info(jobEntity.toString());
            }
        };
    }

    @Bean
    public CommandLineRunner userSeeder()
    {
        return (args) -> {
            for (JobEntity jobEntity : this.jobRepository.findAll()) {
                this.userRepository.save(new UserEntity("Herlyn Carlos", "Paz Vasquez", "71700578", "927315110", "carlos.hcvp@herlyncarlos.com.pe", 1, jobEntity));
            }

            log.info("Usuarios...");
            log.info("---------------------------");

            for (UserEntity userEntity : this.userRepository.findAll()) {
                log.info(userEntity.toString());
            }

        };
    }
}
