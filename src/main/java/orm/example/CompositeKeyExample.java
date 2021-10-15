
package orm.example;

import org.junit.jupiter.api.Test;
import orm.entity.DocumentCopping;
import orm.entity.DocumentEmbedding;
import orm.entity.DocPK;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeKeyExample extends ExampleBase{
    final String text = "Well, Prince, so Genoa and Lucca are now just family estates of the Buonapartes.";

    /**
     * @see <a href="https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html#a132">Primary Keys and Entity Identity</a>
     */
    @Test
    void compositeKeyExample() {
        //create document with entity using @EmbeddedId
        var docOrg = new DocumentEmbedding();
        docOrg.getId().setName("War and peace");
        docOrg.getId().setVersion(1);
        docOrg.setText(text);

        //store it to database
        getEm().getTransaction().begin();
        getEm().persist(docOrg);
        getEm().getTransaction().commit();

        //read document with entity using @IdClass
        var docRep = getEm().find(DocumentCopping.class, new DocPK("War and peace", 1));
        assertEquals(text, docRep.getText());
    }

}
