package com.github.codergate.services;

import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.BranchId;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.repositories.BranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {
    private final String branchURL = "main";
    private final int repositoryID = 101;
    @Mock
    BranchRepository branchRepositoryMock;
    @InjectMocks
    BranchService branchServiceMock;
    @Test
    void addBranchWithAllData() {

        BranchId branchId = new BranchId(repositoryID,  branchURL);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        BranchEntity expected = new BranchEntity(branchId, repositoryEntity);

        ArgumentCaptor<BranchEntity> entityCaptor = ArgumentCaptor.forClass(BranchEntity.class);
        when(branchRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        branchServiceMock.addBranch(branchURL, repositoryID);

        verify(branchRepositoryMock).save(entityCaptor.getValue());
        BranchEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addBranchWithNullData() {
        String nullBranchURL = null;
        int nullRepositoryID = 0;
        BranchId branchId = new BranchId();
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        BranchEntity expected = new BranchEntity(branchId, repositoryEntity);

        ArgumentCaptor<BranchEntity> entityCaptor = ArgumentCaptor.forClass(BranchEntity.class);
        when(branchRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        branchServiceMock.addBranch(nullBranchURL, nullRepositoryID);

        verify(branchRepositoryMock).save(entityCaptor.getValue());
        BranchEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addBranchWithNullBranchURL() {
        String nullBranchURL = null;
        BranchId branchId = new BranchId();
        branchId.setRepositoryId(repositoryID);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        BranchEntity expected = new BranchEntity(branchId, repositoryEntity);

        ArgumentCaptor<BranchEntity> entityCaptor = ArgumentCaptor.forClass(BranchEntity.class);
        when(branchRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        branchServiceMock.addBranch(nullBranchURL, repositoryID);

        verify(branchRepositoryMock).save(entityCaptor.getValue());
        BranchEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addBranchWithNullRepositoryID() {
        int nullRepositoryID = 0;
        BranchId branchId = new BranchId();
        branchId.setBranchUrl(branchURL);
        RepositoryEntity repositoryEntity = new RepositoryEntity();

        BranchEntity expected = new BranchEntity(branchId, repositoryEntity);

        ArgumentCaptor<BranchEntity> entityCaptor = ArgumentCaptor.forClass(BranchEntity.class);
        when(branchRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        branchServiceMock.addBranch(branchURL, nullRepositoryID);

        verify(branchRepositoryMock).save(entityCaptor.getValue());
        BranchEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
