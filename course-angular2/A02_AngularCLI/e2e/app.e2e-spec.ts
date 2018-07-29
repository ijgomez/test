import { A02AngularCLIPage } from './app.po';

describe('a02-angular-cli App', () => {
  let page: A02AngularCLIPage;

  beforeEach(() => {
    page = new A02AngularCLIPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
