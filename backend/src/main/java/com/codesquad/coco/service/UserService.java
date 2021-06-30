package com.codesquad.coco.service;

import com.codesquad.coco.global.exception.business.notfound.NotFoundUserException;
import com.codesquad.coco.domain.oauth.gitoauth.GitHubDeviceType;
import com.codesquad.coco.domain.oauth.gitoauth.GitOauth;
import com.codesquad.coco.domain.oauth.gitoauth.GitUserInfoDTO;
import com.codesquad.coco.domain.room.RoomDAO;
import com.codesquad.coco.domain.room.model.Room;
import com.codesquad.coco.domain.user.ReservationDAO;
import com.codesquad.coco.domain.user.UserDAO;
import com.codesquad.coco.domain.user.model.ReservationStatus;
import com.codesquad.coco.domain.user.model.dto.ReservationDTO;
import oauth.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ReservationDAO reservationDAO;
    private RoomDAO roomDAO;
    private UserDAO userDAO;
    private GitOauth oauth;

    public UserService(ReservationDAO reservationDAO, RoomDAO roomDAO, UserDAO userDAO, GitOauth oauth) {
        this.reservationDAO = reservationDAO;
        this.roomDAO = roomDAO;
        this.userDAO = userDAO;
        this.oauth = oauth;
    }

    public void reservation(Long roomId, Long userId, ReservationDTO reservationDTO) {

        membershipCheck(userId);
        Room room = roomDAO.findById(roomId);
        room.reservationAvailability(reservationDTO);
        reservationDAO.reservation(roomId, userId, reservationDTO, ReservationStatus.RESERVED);
    }

    public void cancelReservation(Long roomId, Long reservationId, Long userId) {
        membershipCheck(userId);
        reservationDAO.cancelReservation(roomId, reservationId, userId, ReservationStatus.CANCEL);
    }

    private void membershipCheck(Long userId) {
        if (userDAO.countUserByGitId(userId) == 0) {
            throw new NotFoundUserException();
        }
    }

    public GitUserInfoDTO loginByGitOauth(String code, GitHubDeviceType gitHubDeviceType) {
        logger.debug(gitHubDeviceType.toString());
        oauth.changeType(gitHubDeviceType);
        AccessToken accessToken = oauth.requestAccessToken(code);
        GitUserInfoDTO userInfo = oauth.requestUserInfo(accessToken);
        insertUser(userInfo, accessToken);
        return userInfo;
    }

    public void insertUser(GitUserInfoDTO userInfo, AccessToken accessToken) {
        if (userDAO.countUserByGitId(userInfo.getId()) != 0) {
            userDAO.updateUser(userInfo, accessToken);
            return;
        }
        userDAO.insertUser(userInfo, accessToken);
    }
}
