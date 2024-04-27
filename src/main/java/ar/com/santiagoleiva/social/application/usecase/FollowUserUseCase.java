package ar.com.santiagoleiva.social.application.usecase;

import ar.com.santiagoleiva.social.application.port.FindUserPort;
import ar.com.santiagoleiva.social.application.port.FollowUserPort;

public class FollowUserUseCase {

    private final FindUserPort findUserPort;
    private final FollowUserPort followUserPort;

    public FollowUserUseCase(FindUserPort findUserPort, FollowUserPort followUserPort) {
        this.findUserPort = findUserPort;
        this.followUserPort = followUserPort;
    }

}
