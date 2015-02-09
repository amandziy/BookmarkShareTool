package org.softserveinc.service;

import org.softserveinc.DTO.BookmarkDTO;
import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.domain.User;
import org.softserveinc.repository.HibernateDAO;
import org.softserveinc.util.ReferenceType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Date;
import java.util.*;

import static org.softserveinc.util.Constants.ROOT_FOLDER;

@Service
public class BookmarkService {
    @Inject
    HibernateDAO hibernateDAO;

    public void saveBookmark(BookmarkDTO bookmarkDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Bookmark bookmark= new Bookmark(bookmarkDTO.getName(),bookmarkDTO.getURL()==null?ROOT_FOLDER:bookmarkDTO.getURL(),bookmarkDTO.getDescription());
        Integer bookmarkID= hibernateDAO.saveBookmarkIntoDB(bookmark);
        //TODO make possibility to add bookmark with reference to team
        BookmarkReference bookmarkReference = new BookmarkReference(bookmarkID, bookmarkDTO.getPath(), new Date(System.currentTimeMillis()),user.getUserId(),ReferenceType.USER);
        hibernateDAO.saveBookmarkReference(bookmarkReference);
    }

    public List<BookmarkReference> getBookmarkReferencesByUserId(String userId) {
        return hibernateDAO.getReferenceByUserId(userId);
    }

    public List<BookmarkReference> getBookmarkReferencesByTeamId(String teamId) {
        return hibernateDAO.getReferenceByTeamId(teamId);
    }

    public  Map<Integer, Bookmark> getMapOfIdsAndBookmarks(List<BookmarkReference> bookmarkReferences){
        Set<Integer> bookmarkIds= new HashSet<>();
        Map<Integer, Bookmark> mapOfIdsAndBookmarks = new HashMap<>();

        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }

        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);

        for (Bookmark bookmark : bookmarks) {
            mapOfIdsAndBookmarks.put(bookmark.getBookmarkId(), bookmark);
        }
        return mapOfIdsAndBookmarks;

    }



}


