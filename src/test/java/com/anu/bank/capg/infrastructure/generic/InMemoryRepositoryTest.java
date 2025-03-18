package com.anu.bank.capg.infrastructure.generic;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class InMemoryRepositoryTest {

    private InMemoryRepository<TestEntity, Integer> repository;

    private InMemoryRepository<InvalidEntity, Integer> repositoryError;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRepository<>();
        repositoryError = new InMemoryRepository<>();
    }

    @Test
    void testSaveAndFindById() {
        // Arrange
        TestEntity entity = new TestEntity(1, "Test Name");

        // Act
        repository.save(entity);
        Optional<TestEntity> retrievedEntity = repository.findById(1);

        // Assert
        assertThat(retrievedEntity).isPresent();
        assertThat(retrievedEntity.get().id()).isEqualTo(1);
        assertThat(retrievedEntity.get().name()).isEqualTo("Test Name");
    }

    @Test
    void testFindByIdNotFound() {
        // Act
        Optional<TestEntity> result = repository.findById(999);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    void testSaveThrowsExceptionWhenIdIsNull() {
        // Arrange
        TestEntity entity = new TestEntity(null, "Invalid Entity");

        // Act & Assert
        assertThatThrownBy(() -> repository.save(entity)).isInstanceOf(RuntimeException.class).hasMessageContaining("ID field is null");
    }

    @Test
    void testSaveThrowsExceptionWhenNoIdFieldExists() {
        // Arrange
        InvalidEntity entity = new InvalidEntity("No ID Field");

        // Act & Assert
        assertThatThrownBy(() -> repositoryError.save(entity)).isInstanceOf(RuntimeException.class).hasMessageContaining("ID field is missing or inaccessible in entity");
    }

    // Test entity with 'id' field
    record TestEntity(Integer id, String name) {
    }

    // Entity without 'id' field (for negative test cases)
    record InvalidEntity(String name) {
    }
}

