package com.example.douanetemporelle;

/**
 * 1. هذه الفئة تجمع مفاتيح الـ Intent
 * 2. المفاتيح لا تكتب مباشرة في الكود، بل تبنى بواسطة دالة ثابتة (static)
 *    تأخذ رقم البطاقة (آخر 4 أرقام) كمعامل
 */
public class Cles {

    // 3. آخر 4 أرقام من بطاقة الطالب (غيّرها برقمك الحقيقي)
    public static final String MATRICULE = "1234";

    // 4. أسماء المفاتيح الأساسية (بدون البادئة)
    public static final String NOM = "NOM_VOYAGEUR";
    public static final String NAISSANCE = "ANNEE_NAISSANCE";
    public static final String DESTINATION = "ANNEE_DESTINATION";
    public static final String MOTIF = "MOTIF_VOYAGE";
    public static final String VERDICT = "VERDICT_FINAL";

    /**
     * 5. دالة ثابتة تبني المفتاح النهائي: matricule + "_" + nom
     * مثال: cle("1234", "NOM_VOYAGEUR") -> "1234_NOM_VOYAGEUR"
     */
    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}
