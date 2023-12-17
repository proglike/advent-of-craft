package document;

import io.vavr.collection.List;
import org.approvaltests.Approvals;
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
                "Autre");

        List<String> recordTypes = List.of((RecordType.values()))
                .map(RecordType::name)
                .append("Autre valeur");

        List<Execution> results = documentTypes
                .flatMap(d -> recordTypes.map(r -> new ExecutionParams(d, r)))
                .map(DocumentTemplateTypeTest::getExecutionResult);

        Approvals.verifyAll("testAllCombinaisons", results);
    }

    private static Execution getExecutionResult(ExecutionParams params) {
        try {
            DocumentTemplateType documentTemplateType = DocumentTemplateType.fromDocumentTypeAndRecordType(params.documentType, params.recordType);
            return new Execution(params, documentTemplateType.name());
        } catch (RuntimeException e) {
            return new Execution(params, e.getMessage());
        }
    }

    private record ExecutionParams(String documentType, String recordType) {
    }

    private record Execution(ExecutionParams params, String result) {
    }

}