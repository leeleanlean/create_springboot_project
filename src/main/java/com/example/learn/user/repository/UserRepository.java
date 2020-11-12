package com.example.learn.user.repository;

import com.example.learn.user.entity.UserEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Slice<UserEntity> findAllByAge(Integer integer, Pageable pageable);

	Object findById(Long id);

	Object deleteById(Long id);
}
