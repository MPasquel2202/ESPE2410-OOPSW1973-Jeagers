package ec.edu.espe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dennis Paucar
 */
 public class ChangeLogManager {

        private DataManager dataManager;

        public ChangeLogManager(DataManager dataManager) {
            this.dataManager = dataManager;
        }

        public Map<String, List<String>> getGroupedChangeLogs() {
            Map<String, List<String>> groupedLogs = new HashMap<>();

           
            List<QuoteChangeLog> quoteChangeLogs = dataManager.getQuoteChangeLogs();
            List<QuoteStatusChangeLog> quoteStatusChangeLogs = dataManager.getQuoteStatusChangeLogs();
            List<StatusChangeLog> statusChangeLogs = dataManager.getStatusChangeLogs();

            
            for (QuoteChangeLog log : quoteChangeLogs) {
                groupedLogs.computeIfAbsent(log.getProjectId(), k -> new ArrayList<>())
                           .add(formatQuoteChangeLog(log));
            }

           
            for (QuoteStatusChangeLog log : quoteStatusChangeLogs) {
                groupedLogs.computeIfAbsent(log.getProjectId(), k -> new ArrayList<>())
                           .add(formatQuoteStatusChangeLog(log));
            }

            
            for (StatusChangeLog log : statusChangeLogs) {
                groupedLogs.computeIfAbsent(log.getProjectId(), k -> new ArrayList<>())
                           .add(formatStatusChangeLog(log));
            }

            return groupedLogs;
        }

        private String formatQuoteChangeLog(QuoteChangeLog log) {
            return String.format("[QuoteChange] Proyecto: %s - Viejo: %.2f, Nuevo: %.2f, Fecha: %s",
                    log.getProjectTitle(),
                    log.getOldQuote(),
                    log.getNewQuote(),
                    log.getChangeDate().toString());
        }

        private String formatQuoteStatusChangeLog(QuoteStatusChangeLog log) {
            return String.format("[QuoteStatusChange] Proyecto: %s - De: %s a %s, Fecha: %s",
                    log.getProjectTitle(),
                    log.getOldQuoteStatus(),
                    log.getNewQuoteStatus(),
                    log.getChangeDate().toString());
        }

        private String formatStatusChangeLog(StatusChangeLog log) {
            return String.format("[StatusChange] Proyecto: %s - De: %s a %s, Fecha: %s",
                    log.getProjectTitle(),
                    log.getOldStatus(),
                    log.getNewStatus(),
                    log.getChangeDate().toString());
        }

        public void displayGroupedLogs() {
            Map<String, List<String>> groupedLogs = getGroupedChangeLogs();

            for (Map.Entry<String, List<String>> entry : groupedLogs.entrySet()) {
                System.out.println("Proyecto ID: " + entry.getKey());
                for (String log : entry.getValue()) {
                    System.out.println("  " + log);
                }
                System.out.println("---------------------------------------------------");
            }
        }
        
 }