package com.jamessipac.library.controller;


import com.jamessipac.library.dto.user.UserRequestUpdate;
import com.jamessipac.library.dto.user.UserResponse;
import com.jamessipac.library.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Arrange
        UserResponse userResponse = new UserResponse();
        when(userService.getUsers()).thenReturn(Collections.singletonList(userResponse));

        // Act
        ResponseEntity<List<UserResponse>> response = userController.getUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(userResponse, response.getBody().get(0));
    }

    @Test
    void testGetUser() {
        // Arrange
        String userId = "123";
        UserResponse userResponse = new UserResponse();
        when(userService.findUserById(userId)).thenReturn(userResponse);

        // Act
        ResponseEntity<UserResponse> response = userController.getUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        String userId = "123";
        UserRequestUpdate userRequestUpdate = new UserRequestUpdate();
        UserResponse updatedUserResponse = new UserResponse();
        when(userService.updateUser(eq(userId), any(UserRequestUpdate.class))).thenReturn(updatedUserResponse);

        // Act
        ResponseEntity<UserResponse> response = userController.updateUser(userId, userRequestUpdate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserResponse, response.getBody());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        String userId = "123";

        // Act
        ResponseEntity<Map<String, String>> response = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("User deleted successfully", response.getBody().get("message"));

        // Verifica que el método deleteUser en el servicio fue llamado
        verify(userService, times(1)).deleteUser(userId);
    }
}
