package com.dpb.store.services.validator;

import com.dpb.store.entites.*;
import com.dpb.store.services.parser.*;
import lombok.Getter;

import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Getter
public class Validator {
    private final List<Product> validProduct = new ArrayList<>();
    private final List<SimiProduct> validSimiProduct = new ArrayList<>();
    private final List<DVD> validDVD = new ArrayList<>();
    private final List<CD> validCD = new ArrayList<>();
    private final List<Book> validBook = new ArrayList<>();
    private final List<com.dpb.store.entites.Review> validReview = new ArrayList<>();
    private final List<Category> validCategory = new ArrayList<>();
    private final List<Category> zumLoeschen = new ArrayList<>();
    private Category up;
    private Category down;
    private final List<Person> validPerson = new ArrayList<>();
    private final List<Store> validStore = new ArrayList<>();
    private final String itemErrors = "{} Item not valid caused by ";
    private final String validErrors = "not valid Value caused by ";
    private final String reviewErrors = "{} Review not valid caused by ";
    private final String itemWarning = "{} Item missing some values ";
    private final String reviewWarning = "{} Review missing some values ";

    public Validator() {
    }

    public Store storeValidator(Shop shop) {
        if (shop != null) {
            Store store = new Store();
            if (shop.getName() == null || shop.getName().replaceAll("\"", "").isEmpty()) {
                log.error("Invalid shop caused by no name {}", shop);
                return null;
            }
            if (shop.getStreet() == null || shop.getStreet().replaceAll("\"", "").isEmpty()) {
                log.error("Invalid shop caused by no Street {}", shop);
                return null;
            }
            if (shop.getZip() == null || shop.getZip().replaceAll("\"", "").isEmpty()) {
                log.error("Invalid shop caused by no Zip {}", shop);
                return null;
            }
            if (!isValidNumber(shop.getZip())) {
                log.error("Invalid shop caused by no Zip {}", shop);
                return null;
            }
            store.setName(shop.getName().replaceAll("\"", ""));
            store.setStreet(shop.getStreet().replaceAll("\"", ""));
            store.setZip(shop.getZip().replaceAll("\"", ""));
            for (Item item : shop.getItem()) {
                if (productValidator(item)) {
                    if (item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("DVD") && DVDValidator(item)) {
                        DVD dvd = convertItemToDVD(item);
                        store.addNewProduct(dvd);
                        dvd.addNewStore(store);
                        if (dvd.getPrice() != 0.0) {
                            store.addNewAvailableProduct(dvd);
                            dvd.addNewAvailableInStore(store);
                        }
                        this.validDVD.add(dvd);
                        this.validProduct.add(dvd);
                    } else if ((item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("music") && CDValidator(item)) || ((item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("Musical") && CDValidator(item)))) {
                        CD cd = convertItemToCD(item);
                        store.addNewProduct(cd);
                        cd.addNewStore(store);
                        if (cd.getPrice() != 0.0) {
                            store.addNewAvailableProduct(cd);
                            cd.addNewAvailableInStore(store);
                        }
                        this.validCD.add(cd);
                        this.validProduct.add(cd);
                    } else if ((item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("Book") && bookValidator(item)) || ((item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("Buch") && bookValidator(item)))) {
                        Book book = convertItemToBook(item);
                        store.addNewProduct(book);
                        book.addNewStore(store);
                        if (book.getPrice() != 0.0) {
                            store.addNewAvailableProduct(book);
                            book.addNewAvailableInStore(store);
                        }
                        this.validBook.add(book);
                        this.validProduct.add(book);
                    }
                }
            }
            this.validStore.add(store);
            return store;
        } else return null;
    }

    private boolean productValidator(Item item) {
        if (item.getAsin() == null || item.getAsin().replaceAll("\"", "").isEmpty()) {
            log.error(itemErrors + "no ID", item);
            return false;
        }
        if (item.getTitle() == null || item.getTitle().replaceAll("\"", "").isEmpty()) {
            log.error(itemErrors + "no title", item);
            return false;
        }
        if (item.getPgroup() == null || item.getPgroup().replaceAll("\"", "").isEmpty()) {
            log.error(itemErrors + "no group", item);
            return false;
        }
        for (Product pr : validProduct) {
            if (item.getAsin().equalsIgnoreCase(pr.getId())) {
                log.error(itemErrors + " Duplicate", item);
                return false;
            }
        }
        if (item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("DVD")) {
            return DVDValidator(item);
        }
        if (item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("CD") || item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("musical")) {
            return CDValidator(item);
        }
        if (item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("Book") || item.getPgroup().replaceAll("\"", "").equalsIgnoreCase("Buch")) {
            return bookValidator(item);
        }
        if ((item.getState() == null || item.getState().replaceAll("\"", "").isEmpty()) || (item.getTheRealImg() == null || item.getTheRealImg().replaceAll("\"", "").isEmpty()) || (item.getListmania().isEmpty() || item.getListmania() == null) || (item.getSimilars().isEmpty() || item.getSimilars() == null)) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    private boolean CDValidator(Item item) {
        if ((item.getMusicspec().getFormat() == null || item.getMusicspec().getFormat().getTheRealValue().replaceAll("\"", "").isEmpty()) || (item.getMusicspec().getBinding() == null || item.getMusicspec().getBinding().replaceAll("\"", "").isEmpty())) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    private boolean DVDValidator(Item item) {
        if ((item.getDvdspec().getFormat() == null || item.getDvdspec().getFormat().replaceAll("\"", "").isEmpty()) || (item.getDvdspec().getAspectratio() == null || item.getDvdspec().getAspectratio().replaceAll("\"", "").isEmpty()) || (item.getDvdspec().getReleasedate() == null || item.getDvdspec().getReleasedate().replaceAll("\"", "").isEmpty())) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    private boolean bookValidator(Item item) {
        if ((item.getBookspec().getBinding() == null || item.getBookspec().getBinding().replaceAll("\"", "").isEmpty()) || (item.getBookspec().getTheRealISBN() == null || item.getBookspec().getTheRealISBN().replaceAll("\"", "").isEmpty())) {
            log.warn(itemWarning, item);
        }
        return true;
    }

    public boolean reviewValidator(com.dpb.store.services.parser.Review review) {
        com.dpb.store.entites.Review entityReview = new com.dpb.store.entites.Review();
        if (review.getContent().isEmpty() || review.getContent() == null) {
            log.error(reviewErrors + " no Content", review.getContent());
            return false;
        }
        entityReview.setContent(review.getContent().replaceAll("\"", ""));
        if (review.getSummery().replaceAll("\"", "").isEmpty() || review.getSummery() == null) {
            log.error(reviewErrors + " no Summery", review);
            return false;
        }
        entityReview.setSummery(review.getSummery().replaceAll("\"", ""));
        if (isValidNumber(review.getHelpful())) {
            int helpful = Integer.parseInt(review.getHelpful().replaceAll("\"", ""));
            if (helpful >= 0) {
                entityReview.setHelpful(helpful);
            } else {
                log.error(reviewErrors + " not valid Helpful", review);
                return false;
            }
        } else {
            return false;
        }
        if (isValidNumber(review.getRating())) {
            int rating = Integer.parseInt(review.getRating().replaceAll("\"", ""));
            if (rating > 0 && rating <= 6) {
                entityReview.setRating(rating);
            } else {
                log.error(reviewErrors + " not valid Rating", review);
                return false;
            }
        } else {
            return false;
        }
        if (isValidDate(review.getReviewdate())) {
            entityReview.setDate(LocalDate.parse(review.getReviewdate().replaceAll("\"", "")));
        } else {
            return false;
        }
        Person person = new Person();
        person.setName(review.getUser().replaceAll("\"", ""));
        this.validPerson.add(person);
        entityReview.setPerson(person);
        for (Product pr : validProduct) {
            if (pr.getId().replaceAll("\"", "").equalsIgnoreCase(review.getProduct().replaceAll("\"", ""))) {
                entityReview.addProduct(pr);
                this.validReview.add(entityReview);
                pr.addNewReview(entityReview);
                return true;
            }
        }
        return false;
    }

    public void categoriesConverter(Categories categories) {
        for (CategoryBean category : categories.getCategory()) {
            validCategory.add(categoryConverter(category, null));
        }
        for (Category category : validCategory) {
            if (category.getName() == null || category.getName().isEmpty()) {
                zumLoeschen.add(category);
            }
        }
        System.out.println(" loeschen " + zumLoeschen.size());
        validCategory.removeAll(zumLoeschen);
    }

    private Category categoryConverter(CategoryBean categoryBean, Category up) {
        Category category = new Category();
        String jo = categoryBean.getCategoryName();
        if (jo != null) {
            if (!jo.isEmpty()) {
                String name = categoryBean.getCategoryName();
                category.setName(name);
                for (Iterator<Category> it = validCategory.iterator(); it.hasNext(); ) {
                    Category x = it.next();
                    if (x.getName() != null) {
                        if (x.getName().equals(jo)) {
                            category = x;
                            it.remove();
                        }
                    }
                }
                if (categoryBean.getItem() != null) {
                    for (String str : categoryBean.getItem()) {
                        for (Product pr : validProduct) {
                            if (pr.getId().equalsIgnoreCase(str)) {
                                category.addNewItem(pr);
                                pr.addNewCategory(category);
                            }
                        }
                    }
                }
                if (categoryBean.getCategory() != null && categoryBean.getCategory().size() != 0) {
                    for (CategoryBean x : categoryBean.getCategory()) {
                        Category y = categoryConverter(x, category);
                        if (category.getChildren() != null) {
                            if (!category.getChildren().contains(y)) category.addNewChild(y);
                        } else category.addNewChild(y);
                    }
                }
            }
            if (up != null) {
                if (category.getParents() != null) {
                    if (!category.getParents().contains(up)) category.addNewParent(up);
                } else category.addNewParent(up);
            }
        }
        this.validCategory.add(category);
        return category;
    }

    private DVD convertItemToDVD(Item item) {
        log.info("Making new DVD ... {}", item);
        DVD dvd = new DVD();
        dvd.setTitle(item.getTitle().replaceAll("\"", ""));
        dvd.setId(item.getAsin().replaceAll("\"", ""));
        if (isValidNumber(item.getSalesrank()))
            dvd.setSalesRank(Integer.parseInt(item.getSalesrank().replaceAll("\"", "")));
        if (isValidDouble(item.getPrice().getPrice()))
            dvd.setPrice(Double.parseDouble(item.getPrice().getPrice().replaceAll("\"", "")));
        dvd.setStatus(item.getPrice().getState().replaceAll("\"", ""));
        dvd.setImage(item.getTheRealImg().replaceAll("\"", ""));
        dvd.setFormat(item.getDvdspec().getFormat().replaceAll("\"", ""));
        dvd.setAspectratio(item.getDvdspec().getAspectratio().replaceAll("\"", ""));
        if (isValidNumber(item.getDvdspec().getRegioncode()))
            dvd.setRegioncode(Integer.parseInt(item.getDvdspec().getRegioncode().replaceAll("\"", "")));
        if (isValidDate(item.getDvdspec().getReleasedate()))
            dvd.setRelease_date(LocalDate.parse(item.getDvdspec().getReleasedate().replaceAll("\"", "")));
        if (isValidNumber(item.getDvdspec().getRunningtime()))
            dvd.setRunning_time(Integer.parseInt(item.getDvdspec().getRunningtime().replaceAll("\"", "")));
        if (isValidNumber(item.getDvdspec().getTheatr_release()))
            dvd.setTheater_release(Integer.parseInt(item.getDvdspec().getTheatr_release().replaceAll("\"", "")));
        if (item.getSimilars() != null) {
            for (Item i : item.getSimilars()) {
                if (i != null) {
                    if ((i.getAsin() != null || !i.getAsin().replaceAll("\"", "").isEmpty()) && (i.getTheRealTitle() != null || !i.getTheRealTitle().replaceAll("\"", "").isEmpty())) {
                        SimiProduct simiProduct = new SimiProduct();
                        simiProduct.setId(i.getAsin().replaceAll("\"", ""));
                        simiProduct.setTitle(i.getTheRealTitle().replaceAll("\"", ""));
                        dvd.addNewSimProduct(simiProduct);
                        simiProduct.addNewProduct(dvd);
                    }
                }
            }
        }
        if (item.getListmania() != null) {
            for (GeneralField str : item.getListmania()) {
                dvd.addNewListMania(str.getTheRealValue().replaceAll("\"", ""));
            }
        }
        if (item.getStudios() != null) {
            for (GeneralField str : item.getStudios()) {
                dvd.addNewStudio(str.getTheRealValue().replaceAll("\"", ""));
            }
        }
        if (item.getActors() != null) {
            for (GeneralField str : item.getActors()) {
                Person person = new Person();
                person.setName(str.getTheRealValue().replaceAll("\"", ""));
                this.validPerson.add(person);
                dvd.addNewActor(person);
            }
        }
        if (item.getCreators() != null) {
            for (GeneralField str : item.getCreators()) {
                Person person = new Person();
                person.setName(str.getTheRealValue().replaceAll("\"", ""));
                this.validPerson.add(person);
                dvd.addNewCreator(person);
            }
        }
        if (item.getDirectors() != null) {
            for (GeneralField str : item.getDirectors()) {
                Person person = new Person();
                person.setName(str.getTheRealValue().replaceAll("\"", ""));
                this.validPerson.add(person);
                dvd.addNewDirector(person);
            }
        }
        return dvd;
    }

    private CD convertItemToCD(Item item) {
        log.info("Making new CD ... {}", item);
        CD cd = new CD();
        cd.setId(item.getAsin().replaceAll("\"", ""));
        cd.setTitle(item.getTitle().replaceAll("\"", ""));
        if (isValidNumber(item.getSalesrank().replaceAll("\"", "")))
            cd.setSalesRank(Integer.parseInt(item.getSalesrank().replaceAll("\"", "")));
        if (isValidDouble(item.getPrice().getPrice()))
            cd.setPrice(Double.parseDouble(item.getPrice().getPrice().replaceAll("\"", "")));
        cd.setStatus(item.getPrice().getState().replaceAll("\"", ""));
        cd.setImage(item.getTheRealImg().replaceAll("\"", ""));
        cd.setFormat(item.getMusicspec().getFormat().getTheRealValue().replaceAll("\"", ""));
        cd.setBinding(item.getMusicspec().getBinding().replaceAll("\"", ""));
        if (isValidNumber(item.getMusicspec().getNum_discs()))
            cd.setDisc_Nr(Integer.parseInt(item.getMusicspec().getNum_discs().replaceAll("\"", "")));
        if (isValidDate(item.getMusicspec().getReleasedate()))
            cd.setDate(LocalDate.parse(item.getMusicspec().getReleasedate().replaceAll("\"", "")));
        if (item.getSimilars() != null) {
            for (Item i : item.getSimilars()) {
                if (i != null) {
                    if ((i.getAsin() != null || !i.getAsin().replaceAll("\"", "").isEmpty()) && (i.getTheRealTitle() != null || !i.getTheRealTitle().replaceAll("\"", "").isEmpty())) {
                        SimiProduct simiProduct = new SimiProduct();
                        simiProduct.setId(i.getAsin().replaceAll("\"", ""));
                        simiProduct.setTitle(i.getTheRealTitle().replaceAll("\"", ""));
                        cd.addNewSimProduct(simiProduct);
                        simiProduct.addNewProduct(cd);
                    }
                }
            }
        }
        if (item.getListmania() != null) {
            for (GeneralField str : item.getListmania()) {
                cd.addNewListMania(str.getTheRealValue().replaceAll("\"", ""));
            }
        }
        if (item.getTracks() != null) {
            for (GeneralField str : item.getTracks()) {
                cd.addNewTrack(str.getTheRealValue().replaceAll("\"", ""));
            }
        }
        if (item.getLabels() != null) {
            for (GeneralField str : item.getLabels()) {
                cd.addNewLabels(str.getTheRealValue().replaceAll("\"", ""));
            }
        }
        if (item.getArtists() != null) {
            for (GeneralField str : item.getArtists()) {
                Person person = new Person();
                person.setName(str.getTheRealValue().replaceAll("\"", ""));
                this.validPerson.add(person);
                cd.addNewArtist(person);
            }
        }
        return cd;
    }

    private Book convertItemToBook(Item item) {
        log.info("Making new Book ... {}", item);
        Book book = new Book();
        book.setId(item.getAsin().replaceAll("\"", ""));
        book.setTitle(item.getTitle().replaceAll("\"", ""));
        if (isValidNumber(item.getSalesrank()))
            book.setSalesRank(Integer.parseInt(item.getSalesrank().replaceAll("\"", "")));
        if (isValidDouble(item.getPrice().getPrice()))
            book.setPrice(Double.parseDouble(item.getPrice().getPrice().replaceAll("\"", "")));
        book.setStatus(item.getPrice().getState().replaceAll("\"", ""));
        book.setImage(item.getTheRealImg().replaceAll("\"", ""));
        book.setBinding(item.getBookspec().getBinding().replaceAll("\"", ""));
        book.setEdition(item.getBookspec().getTheRealEdition().replaceAll("\"", ""));
        book.setIsbn(item.getBookspec().getTheRealISBN().replaceAll("\"", ""));
        if (isValidNumber(item.getBookspec().getPages()))
            book.setPage(Integer.parseInt(item.getBookspec().getPages().replaceAll("\"", "")));
        if (isValidNumber(item.getBookspec().getBookPackage().getHeight()))
            book.setHeight(Integer.parseInt(item.getBookspec().getBookPackage().getHeight().replaceAll("\"", "")));
        if (isValidNumber(item.getBookspec().getBookPackage().getLength()))
            book.setLength(Integer.parseInt(item.getBookspec().getBookPackage().getLength().replaceAll("\"", "")));
        if (isValidNumber(item.getBookspec().getBookPackage().getWeight()))
            book.setWeight(Integer.parseInt(item.getBookspec().getBookPackage().getWeight().replaceAll("\"", "")));
        if (isValidDate(item.getBookspec().getTheRealPublication()))
            book.setPublication(LocalDate.parse(item.getBookspec().getTheRealPublication().replaceAll("\"", "")));
        if (item.getSimilars() != null) {
            for (Item i : item.getSimilars()) {
                if (i != null) {
                    if ((i.getAsin() != null || !i.getAsin().replaceAll("\"", "").isEmpty()) && (i.getTheRealTitle() != null || !i.getTheRealTitle().replaceAll("\"", "").isEmpty())) {
                        SimiProduct simiProduct = new SimiProduct();
                        simiProduct.setId(i.getAsin().replaceAll("\"", ""));
                        simiProduct.setTitle(i.getTheRealTitle().replaceAll("\"", ""));
                        book.addNewSimProduct(simiProduct);
                        simiProduct.addNewProduct(book);
                    }
                }
            }
        }
        if (item.getListmania() != null) {
            for (GeneralField str : item.getListmania()) {
                book.addNewListMania(str.getTheRealValue().replaceAll("\"", ""));
            }
        }
        if (item.getAuthors() != null) {
            for (GeneralField str : item.getAuthors()) {
                Person person = new Person();
                person.setName(str.getTheRealValue().replaceAll("\"", ""));
                this.validPerson.add(person);
                book.addNewAuthor(person);
            }
        }
        if (item.getPublishers() != null) {
            for (GeneralField str : item.getPublishers()) {
                book.addNewPublisher(str.getTheRealValue().replaceAll("\"", ""));
            }
        }
        return book;
    }

    private boolean isValidNumber(String str) {
        if (str != null && !str.replaceAll("\"", "").isEmpty()) {
            try {
                Integer.parseInt(str.replaceAll("\"", ""));
            } catch (NumberFormatException e) {
                log.warn("invalid INTEGER, can not convert to Integer {}", str);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidDouble(String str) {
        if (str != null && !str.replaceAll("\"", "").isEmpty()) {
            try {
                Double.parseDouble(str.replaceAll("\"", ""));
            } catch (NumberFormatException e) {
                log.warn("invalid DOUBLE, can not convert to Double {}", str);
                return false;
            }
            return true;
        } else return false;
    }

    private boolean isValidDate(String str) {
        if (str != null && !str.replaceAll("\"", "").isEmpty()) {
            try {
                LocalDate.parse(str.replaceAll("\"", ""));
            } catch (DateTimeException e) {
                log.warn("invalid DATE, can not convert to LocalDate {}", str);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
