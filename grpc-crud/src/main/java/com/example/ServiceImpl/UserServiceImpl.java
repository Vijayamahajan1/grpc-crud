package com.example.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.UserServiceGrpc.UserServiceImplBase;
import io.grpc.stub.StreamObserver;
import com.example.Empty;
import com.example.Response;
import com.example.User;
import com.example.UserId;
import com.example.UserList;
import com.example.Service.UserService;

@Service
public class UserServiceImpl extends UserServiceImplBase {

    @Autowired
    UserService userService;

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void createUser(User request, StreamObserver<User> responseObserver) {
       com.example.Model.User user = new com.example.Model.User();
       user.setName(request.getName());
       user.setEmail(request.getEmail());
       userService.save(user);
       logger.info("User:", user);
       responseObserver.onNext(request);
       responseObserver.onCompleted();
    }

    @Override
    public void getUser(UserId request, StreamObserver<User> responseObserver) {
       Long id  = (long)request.getId();
       com.example.Model.User user = userService.get(id);
       User.Builder response = User.newBuilder();
       response.setName(user.getName());
       response.setEmail(user.getEmail());
       responseObserver.onNext(response.build());  
       responseObserver.onCompleted();        
    }

    @Override
    public void updateUser(User request, StreamObserver<User> responseObserver) {
        com.example.Model.User user = new com.example.Model.User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        userService.update(user);
        logger.info("User:", user);
        responseObserver.onNext(request);
        responseObserver.onCompleted();

    }

   @Override
   public void getAllUsers(Empty request, StreamObserver<UserList> responseObserver) {
        List<com.example.Model.User> users = userService.getAll();
        List<User> grpcUsers = users.stream().map(user -> User.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .build())
                .collect(Collectors.toList());
                
        UserList response = UserList.newBuilder().addAllUsers(grpcUsers).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
   }

    @Override
    public void deleteUser(UserId request, StreamObserver<Response> responseObserver) {      
        Long id = request.getId();
        userService.delete(id);
        logger.info("Data are deleted");
        String msg = "Data are deleted";
        Response response = Response.newBuilder().setMessage(msg).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
