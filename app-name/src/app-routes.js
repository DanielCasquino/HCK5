import {
  HomePage,
  GroupsPage,
  ProfilePage,
  PersonsPage,
  TypesPage,
} from './pages';
import { withNavigationWatcher } from './contexts/navigation';

const routes = [
  {
    path: '/types',
    element: TypesPage,
  },
  {
    path: '/groups',
    element: GroupsPage,
  },
  {
    path: '/persons',
    element: PersonsPage,
  },
  {
    path: '/profile',
    element: ProfilePage,
  },
  {
    path: '/home',
    element: HomePage,
  },
];

export default routes.map((route) => {
  return {
    ...route,
    element: withNavigationWatcher(route.element, route.path),
  };
});
