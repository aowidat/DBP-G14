package com.dpb.store.services.validator;

import com.dpb.store.entites.*;
import com.dpb.store.services.parser.GeneralField;
import com.dpb.store.services.parser.Item;
import com.dpb.store.services.parser.Shop;
import com.dpb.store.services.parser.Review;
import lombok.Getter;

import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class Validator {
    private final List<DVD> validDVD = new ArrayList<>();
    private final List<CD> validCD = new ArrayList<>();
    private final List<Book> validBook = new ArrayList<>();
    private final List<com.dpb.store.entites.Review> validReview = new ArrayList<>();
    private final List<Category> validCategory = new ArrayList<>();
    private final List<Person> validPerson = new ArrayList<>();
    private final List<Store> validStore = new ArrayList<>();
    private final String itemErrors = "{} Item not valid caused by ";
    private final String reviewErrors = "{} Review not valid caused by ";
    private final String itemWarning = "{} Item missing some values ";
    private final String reviewWarning = "{} Review missing some values ";

    public Validator() {
    }

    public Store storeValidator(Shop shop) {
        if (shop != null) {
            Store store = new Store();
            if (shop.getName() == null || shop.getName().isEmpty()) {
                log.error("Invalid shop caused by no name {}", shop);
                return null;
            }
            if (shop.getStreet() == null || shop.getStreet().isEmpty()) {
                log.error("Invalid shop caused by no Street {}", shop);
                return null;
            }
            if (shop.getZip() == null || shop.getZip().isEmpty()) {
                log.error("Invalid shop caused by no Zip {}", shop);
                return null;
            }
            int zip;
            try {
                zip = Integer.parseInt(shop.getZip());
            } catch (NumberFormatException e) {
                log.error("invalid shop caused by invalid zip {}", shop.getZip());
                return null;
            }
            store.setName(shop.getName());
            store.setStreet(shop.getStreet());
            store.setZip(zip);
            for (Item item : shop.getItem()) {
                if (productValidator(item)) {
                    if (item.getPgroup().equalsIgnoreCase("DVD") && DVDValidator(item)) {
                        DVD dvd = convertItemToDVD(item);
                        store.addNewProduct(dvd);
                        this.validDVD.add(dvd);
                    }
                    if (item.getPgroup().equalsIgnoreCase("CD") && CDValidator(item)) {
                        CD cd = convertItemToCD(item);
                        store.addNewProduct(cd);
                        this.validCD.add(cd);
                    }
                    if (item.getPgroup().equalsIgnoreCase("Book") && bookValidator(item)) {
                        Book book = convertItemToBook(item);
                        store.addNewProduct(book);
                        this.validBook.add(book);
                    }
                }
            }
            this.validStore.add(store);
            return store;
        } else return null;
    }


    public boolean productValidator(Item item) {
        if (item.getAsin() == null || item.getAsin().isEmpty()) {
            log.error(itemErrors + "no ID", item);
            return false;
        }
        if (item.getTitle() == null || item.getTitle().isEmpty()) {
            log.error(itemErrors + "no title", item);
            return false;
        }
        if (item.getPgroup() == null || item.getPgroup().isEmpty()) {
            log.error(itemErrors + "no group", item);
            return false;
        }
        try {
            Integer.parseInt(item.getSalesrank());
        } catch (NumberFormatException e) {
            log.error(itemErrors + "fail to convert the value to Integer ", e.getMessage());
            return false;
        }
        if (item.getPgroup().equalsIgnoreCase("DVD") && DVDValidator(item)) {
            return DVDValidator(item);
        }
        if (item.getPgroup().equalsIgnoreCase("CD") && CDValidator(item)) {
            return CDValidator(item);
        }
        if (item.getPgroup().equalsIgnoreCase("Book") && bookValidator(item)) {
            return bookValidator(item);
        }
        if ((item.getState() == null || item.getState().isEmpty()) || (item.getTheRealImg() == null || item.getTheRealImg().isEmpty()) || (item.getListmania().isEmpty() || item.getListmania() == null) || (item.getSimilars().isEmpty() || item.getSimilars() == null)) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    public boolean CDValidator(Item item) {
        try {
            Integer.parseInt(item.getMusicspec().getNum_discs());
            Integer.parseInt(item.getPrice().getPrice());
            LocalDate.parse(item.getMusicspec().getReleasedate());
        } catch (NumberFormatException | DateTimeException e) {
            log.error(itemErrors + "fail to convert the value ", e.getMessage());
            return false;
        }
        if ((item.getMusicspec().getFormat() == null || item.getMusicspec().getFormat().getTheRealValue().isEmpty()) || (item.getMusicspec().getBinding() == null || item.getMusicspec().getBinding().isEmpty())) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    public boolean DVDValidator(Item item) {
        try {
            Integer.parseInt(item.getDvdspec().getRunningtime());
            Integer.parseInt(item.getDvdspec().getTheatr_release());
            Integer.parseInt(item.getPrice().getPrice());
            LocalDate.parse(item.getDvdspec().getReleasedate());
        } catch (NumberFormatException | DateTimeException e) {
            log.error(itemErrors + "fail to convert the value ", e.getMessage());
            return false;
        }
        if ((item.getDvdspec().getFormat() == null || item.getDvdspec().getFormat().isEmpty()) || (item.getDvdspec().getAspectratio() == null || item.getDvdspec().getAspectratio().isEmpty()) || (item.getDvdspec().getReleasedate() == null || item.getDvdspec().getReleasedate().isEmpty())) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    public boolean bookValidator(Item item) {
        try {
            Integer.parseInt(item.getBookspec().getEdition());
            Integer.parseInt(item.getBookspec().getPages());
            Integer.parseInt(item.getPrice().getPrice());
            Integer.parseInt(item.getBookspec().getBookPackage().getWeight());
            Integer.parseInt(item.getBookspec().getBookPackage().getLength());
            Integer.parseInt(item.getBookspec().getBookPackage().getHeight());
            LocalDate.parse(item.getBookspec().getPublication());
        } catch (NumberFormatException | DateTimeException e) {
            log.error(itemErrors + "fail to convert the value ", e.getMessage());
            return false;
        }
        if ((item.getBookspec().getBinding() == null || item.getBookspec().getBinding().isEmpty()) || (item.getBookspec().getTheRealISBN() == null || item.getBookspec().getTheRealISBN().isEmpty())) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    public boolean ReviewValidator(Review review) {
        com.dpb.store.entites.Review entityReview = new com.dpb.store.entites.Review();
        if (review.getContent().isEmpty() || review.getContent() == null) {
            log.error(reviewErrors + " no Content", review);
            return false;
        }
        entityReview.setContent(review.getContent());
        if (review.getSummery().isEmpty() || review.getSummery() == null) {
            log.error(reviewErrors + " no Summery", review);
            return false;
        }
        entityReview.setSummery(review.getSummery());
        try {
            int helpful = Integer.parseInt(review.getHelpful());
            if (helpful < 0) {
                log.error(reviewErrors + " not valid Helpful", review);
                return false;
            } else {
                entityReview.setHelpful(helpful);
            }
        } catch (NumberFormatException e) {
            log.error(reviewErrors + " not valid Helpful", e.getMessage());
            return false;
        }
        try {
            int rating = Integer.parseInt(review.getRating());
            if (rating < 1 || rating > 6) {
                log.error(reviewErrors + " not valid Rating", review);
                return false;
            } else {
                entityReview.setRating(rating);
            }
        } catch (NumberFormatException e) {
            log.error(reviewErrors + " not valid Rating", e.getMessage());
            return false;
        }
        try {
            entityReview.setDate(LocalDate.parse(review.getReviewdate()));
        } catch (DateTimeException e) {
            log.error(reviewErrors + " not valid Date", e.getMessage());
            return false;
        }
        Person person = new Person();
        person.setName(review.getUser());
        this.validPerson.add(person);
        entityReview.setPerson(person);
        for (Store st : this.validStore) {
            for (Product pr : st.getProducts()) {
                if (pr.getId().equalsIgnoreCase(review.getProduct())) {
                    entityReview.setProduct(pr);
                    this.validReview.add(entityReview);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean categoryValidator(Category category) {
        boolean valid = false;

        return true;
    }

    public DVD convertItemToDVD(Item item) {
        DVD dvd = new DVD();
        dvd.setTitle(item.getTitle());
        dvd.setSalesRank(Integer.parseInt(item.getSalesrank()));
        dvd.setPrice(Integer.parseInt(item.getPrice().getPrice()));
        dvd.setStatus(item.getPrice().getState());
        dvd.setImage(item.getTheRealImg());
        dvd.setFormat(item.getDvdspec().getFormat());
        dvd.setAspectratio(item.getDvdspec().getAspectratio());
        dvd.setRegioncode(Integer.parseInt(item.getDvdspec().getRegioncode()));
        dvd.setRelease_date(LocalDate.parse(item.getDvdspec().getReleasedate()));
        dvd.setRunning_time(Integer.parseInt(item.getDvdspec().getRunningtime()));
        dvd.setTheater_release(Integer.parseInt(item.getDvdspec().getTheatr_release()));
        if (item.getSimilars() != null) {
            for (Item i : item.getSimilars()) {
                Product product = new Product();
                product.setId(i.getAsin());
                product.setTitle(i.getTheRealTitle());
                dvd.addNewSimProcuct(product);
            }
        }
        if (item.getListmania() != null) {
            for (GeneralField str : item.getListmania()) {
                dvd.addNewListMania(str.getTheRealValue());
            }
        }
        if (item.getStudios() != null) {
            for (GeneralField str : item.getStudios()) {
                dvd.addNewStudio(str.getTheRealValue());
            }
        }
        if (item.getActors() != null) {
            for (GeneralField str : item.getActors()) {
                Person person = new Person();
                person.setName(str.getTheRealValue());
                this.validPerson.add(person);
                dvd.addNewActor(person);
            }
        }
        if (item.getCreators() != null) {
            for (GeneralField str : item.getCreators()) {
                Person person = new Person();
                person.setName(str.getTheRealValue());
                this.validPerson.add(person);
                dvd.addNewCreator(person);
            }
        }
        if (item.getDirectors() != null) {
            for (GeneralField str : item.getDirectors()) {
                Person person = new Person();
                person.setName(str.getTheRealValue());
                this.validPerson.add(person);
                dvd.addNewDirector(person);
            }
        }
        return dvd;
    }

    public CD convertItemToCD(Item item) {
        CD cd = new CD();
        cd.setTitle(item.getTitle());
        cd.setSalesRank(Integer.parseInt(item.getSalesrank()));
        cd.setPrice(Integer.parseInt(item.getPrice().getPrice()));
        cd.setStatus(item.getPrice().getState());
        cd.setImage(item.getTheRealImg());
        cd.setFormat(item.getMusicspec().getFormat().getTheRealValue());
        cd.setBinding(item.getMusicspec().getBinding());
        cd.setDisc_Nr(Integer.parseInt(item.getMusicspec().getNum_discs()));
        cd.setDate(LocalDate.parse(item.getMusicspec().getReleasedate()));
        if (item.getSimilars() != null) {
            for (Item i : item.getSimilars()) {
                Product product = new Product();
                product.setId(i.getAsin());
                product.setTitle(i.getTheRealTitle());
                cd.addNewSimProcuct(product);
            }
        }
        if (item.getListmania() != null) {
            for (GeneralField str : item.getListmania()) {
                cd.addNewListMania(str.getTheRealValue());
            }
        }
        if (item.getTracks() != null) {
            for (GeneralField str : item.getTracks()) {
                cd.addNewTrack(str.getTheRealValue());
            }
        }
        if (item.getLabels() != null) {
            for (GeneralField str : item.getLabels()) {
                cd.addNewLabels(str.getTheRealValue());
            }
        }
        if (item.getArtists() != null) {
            for (GeneralField str : item.getArtists()) {
                Person person = new Person();
                person.setName(str.getTheRealValue());
                this.validPerson.add(person);
                cd.addNewArtist(person);
            }
        }
        return cd;
    }

    public Book convertItemToBook(Item item) {
        Book book = new Book();
        book.setTitle(item.getTitle());
        book.setSalesRank(Integer.parseInt(item.getSalesrank()));
        book.setPrice(Integer.parseInt(item.getPrice().getPrice()));
        book.setStatus(item.getPrice().getState());
        book.setImage(item.getTheRealImg());
        book.setBinding(item.getBookspec().getBinding());
        book.setEdition(Integer.parseInt(item.getBookspec().getEdition()));
        book.setIsbn(item.getBookspec().getTheRealISBN());
        book.setPage(Integer.parseInt(item.getBookspec().getPages()));
        book.setHeight(Integer.parseInt(item.getBookspec().getBookPackage().getHeight()));
        book.setLength(Integer.parseInt(item.getBookspec().getBookPackage().getLength()));
        book.setWeight(Integer.parseInt(item.getBookspec().getBookPackage().getWeight()));
        book.setPublication(LocalDate.parse(item.getBookspec().getPublication()));
        if (item.getSimilars() != null) {
            for (Item i : item.getSimilars()) {
                Product product = new Product();
                product.setId(i.getAsin());
                product.setTitle(i.getTheRealTitle());
                book.addNewSimProcuct(product);
            }
        }
        if (item.getListmania() != null) {
            for (GeneralField str : item.getListmania()) {
                book.addNewListMania(str.getTheRealValue());
            }
        }
        if (item.getAuthors() != null) {
            for (GeneralField str : item.getAuthors()) {
                Person person = new Person();
                person.setName(str.getTheRealValue());
                this.validPerson.add(person);
                book.addNewAuthor(person);
            }
        }
        if (item.getPublishers() != null) {
            for (GeneralField str : item.getPublishers()) {
                book.addNewPublisher(str.getTheRealValue());
            }
        }
        return book;
    }

}
