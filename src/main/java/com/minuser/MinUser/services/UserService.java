package com.minuser.MinUser.services;

import com.minuser.MinUser.entities.JobEntity;
import com.minuser.MinUser.entities.UserEntity;
import com.minuser.MinUser.models.UserEntityCreateRequest;
import com.minuser.MinUser.models.UserEntityQueryParams;
import com.minuser.MinUser.models.UserEntityUpdateRequest;
import com.minuser.MinUser.repositories.JobRepository;
import com.minuser.MinUser.repositories.UserRepository;
import com.minuser.MinUser.specifications.UserSpecifications;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {
  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  private UserRepository userRepository;

  private JobRepository jobRepository;

  @Autowired
  public UserService(UserRepository userRepository,
                     JobRepository jobRepository) {
    this.userRepository = userRepository;
    this.jobRepository = jobRepository;
  }

  /**
   * Save the new user
   *
   * @param request - The request with the data of the new user
   * @return - The new user
   */
  public UserEntity create(UserEntityCreateRequest request) {
    try {
      UserEntity userEntity = new UserEntity();

      userEntity.setName(request.getName());
      userEntity.setLastname(request.getLastname());
      userEntity.setDocument(request.getDocument());
      userEntity.setPhone(request.getPhone());
      userEntity.setEmail(request.getEmail());
      Optional<JobEntity> jobEntity =
          this.jobRepository.findById(request.getIdJob());
      if (jobEntity.isEmpty()) {
        throw new RuntimeException("Invalid job ID");
      }
      userEntity.setJob(jobEntity.get());
      return this.userRepository.save(userEntity);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Get the user
   *
   * @param id - Id of the user
   * @return the user with the given id or `optional empty`
   */
  public Optional<UserEntity> show(Long id) {
    return this.userRepository.findById(id);
  }

  /**
   * Get a list without user paging
   *
   * @param queryParams - Query params with the search rules
   * @return the list of user
   */
  public Iterable<UserEntity> get(UserEntityQueryParams queryParams) {
    Specification<UserEntity> specification = this.filter(queryParams);
    return this.userRepository.findAll(specification);
  }

  /**
   * Get a list with user paging
   *
   * @param queryParams - Query params with the search rules
   * @param pageable - Query param with the paging rules
   * @return the list of user
   */
  public Page<UserEntity> list(UserEntityQueryParams queryParams,
                               Pageable pageable) {
    Specification<UserEntity> specification = this.filter(queryParams);
    return this.userRepository.findAll(specification, pageable);
  }

  /**
   * Update a user
   *
   * @param request - The request with the data of the user for update
   * @return - The updated user
   */
  public UserEntity update(UserEntityUpdateRequest request) {
    try {
      Optional<UserEntity> userEntity =
          this.userRepository.findById(request.getId());
      Optional<JobEntity> jobEntity =
          this.jobRepository.findById(request.getIdJob());
      if (userEntity.isEmpty()) {
        throw new RuntimeException("No existe el id de usuario");
      }
      if (jobEntity.isEmpty()) {
        throw new RuntimeException("No existe el id del trabajo");
      }
      UserEntity user = userEntity.get();
      user.setName(request.getName());
      user.setLastname(request.getLastname());
      user.setDocument(request.getDocument());
      user.setEmail(request.getEmail());
      user.setPhone(request.getPhone());
      user.setJob(jobEntity.get());
      this.userRepository.save(user);
      return user;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Delete a user
   *
   * @param id - The id of the user
   * @return void
   */
  public void delete(Long id) {
    try {
      this.userRepository.deleteById(id);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Get a `Specification` applying the search rules
   *
   * @param queryParams The search rules
   * @return The `Specification` with search rules of users
   */
  private Specification<UserEntity> filter(UserEntityQueryParams queryParams) {
    Specification<UserEntity> specification = Specification.where(null);
    if (queryParams == null) {
      log.info("No estas enviado Query Params...");
      return specification;
    }

    log.info("Los query params son...");
    log.info(queryParams.toString());

    if (queryParams.id() != null && !queryParams.id().isEmpty()) {
      specification =
          specification.and(UserSpecifications.byIds(queryParams.id()));
    }

    if (queryParams.search() != null && !queryParams.search().equals("")) {
      specification =
          specification.and(UserSpecifications.bySearch(queryParams.search()));
    }

    if (queryParams.name() != null && !queryParams.name().equals("")) {
      specification =
          specification.and(UserSpecifications.byName(queryParams.name()));
    }

    if (queryParams.lastname() != null && !queryParams.lastname().equals("")) {
      specification = specification.and(
          UserSpecifications.byLastname(queryParams.lastname()));
    }

    if (queryParams.document() != null && !queryParams.document().equals("")) {
      specification = specification.and(
          UserSpecifications.byDocument(queryParams.document()));
    }

    if (queryParams.phone() != null && !queryParams.phone().equals("")) {
      specification =
          specification.and(UserSpecifications.byPhone(queryParams.phone()));
    }

    if (queryParams.email() != null && !queryParams.email().equals("")) {
      specification =
          specification.and(UserSpecifications.byEmail(queryParams.email()));
    }

    specification =
        specification.and(UserSpecifications.bySort(queryParams.sort()));

    return specification;
  }
}
