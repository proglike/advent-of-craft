package document;

import io.vavr.collection.List;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

class DocumentTemplateTypeTest {

    @Test
    void approvalTest() {

        List<String> documentTypes = List.of(
                "DEER",
                "AUTP",
                "AUTM",
                "SPEC",
                "GLPP",
                "GLPM",
                "Autre Document Type");

        List<String> recordTypes = List.of((RecordType.values()))
                .map(RecordType::name)
                .append("Autre Record Type");

        CombinationApprovals.verifyAllCombinations(
                DocumentTemplateType::fromDocumentTypeAndRecordType,
                documentTypes.toJavaArray(String[]::new),
                recordTypes.toJavaArray(String[]::new));
    }

}