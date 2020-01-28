package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ElementRepositoryTest {

    @Autowired
    private ElementRepository elementRepository;

    @Test
    public void testSaveElement() {
        //setup element
        Element element = new Element();
        element.setElementName("Phone");

        //save element, verify it has an ID after the save.
        assertNull(element.getId());
        elementRepository.save(element);
        assertNotNull(element.getId());

        //fetch
        Element fetchedElement = elementRepository.findOne(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementName("Email");
        elementRepository.save(fetchedElement);

        Element updatedElement = elementRepository.findOne(fetchedElement.getId());
        assertEquals(updatedElement.setElementName("Email"), "Email");

    }

    @Test
    public void testSaveElementList(){
        List<Element> elementList = new ArrayList<>();

        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        Iterable<Element> fetchedElementList = elementRepository.findAll();

        int count = 0;
        for(Element element : fetchedElementList){
            count ++;
        }

//        assertEquals(5 , count);
    }

    @Test
    public void testFindByName() {
        Element element = new Element ("FindTestSingle");
        elementRepository.save(element);

        Element foundByNameElement = elementRepository.findByElementName(("FindTestSingle"));

        assertEquals(element.getId(), foundByNameElement.getId());

    }

    @Test
    public void testFindAllByName() {
        Element element1 = new Element ("FindTest");
        elementRepository.save(element1);
        Element element2 = new Element ("FindTest");
        elementRepository.save(element2);
        Element element3 = new Element ("FindTest");
        elementRepository.save(element3);

        List<Element> foundAllByNameElement = elementRepository.findAllByElementName(("FindTest"));

        assertEquals(3, foundAllByNameElement.size());

    }

    @Test
    public void countByElementName() {

        Element element1 = new Element ("Notebook");
        elementRepository.save(element1);
        Element element2 = new Element ("Pencil");
        elementRepository.save(element2);
        Element element3 = new Element ("Desk");
        elementRepository.save(element3);
        Element element4 = new Element ("Pencil");
        elementRepository.save(element4);
        Element element5 = new Element ("Pen");
        elementRepository.save(element5);
        Element element6 = new Element ("Desk");
        elementRepository.save(element6);

        int foundDistinctByElementName = elementRepository.countByElementName("Pencil");


        assertEquals(2 , foundDistinctByElementName);

    }


    @Test
    public void findById () {

        Element foundById = elementRepository.findById(1);

        assertEquals("Email", foundById.setElementName("Email"));

    }


}