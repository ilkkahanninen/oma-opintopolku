import i18n from 'i18next';
import Backend from 'i18next-xhr-backend';
import { reactI18nextModule } from 'react-i18next';
import LngDetector from 'i18next-browser-languagedetector';
import DomainDetector from './i18n-domaindetector';
LngDetector.use(DomainDetector);

i18n
  .use(Backend)
  .use(LngDetector)
  .use(reactI18nextModule)
  .init({
    fallbackLng: 'fi',
    ns: ['common', 'home', 'selection'],
    defaultNS: 'common',
    debug: true,
    interpolation: {
      escapeValue: false,
    },
    react: {
      defaultTransParent: 'div',
      wait: true
    },
    backend: {
      loadPath: '/oma-opintopolku/locales/{{lng}}/{{ns}}.json'
    },
    detection: {
      order: ['cookie', 'domainDetector'],
      lookupCookie: 'lang',
    }
  });

export default i18n;
