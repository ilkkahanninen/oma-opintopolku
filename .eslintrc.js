// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint',
    ecmaFeatures: {
      jsx: true,
      modules: true
    },
  },
  plugins: ['react'],
  env: {
    browser: true,
  },
  extends: ['airbnb'],
  settings: {
    'import/resolver': {
      webpack: {
        config: 'build/webpack.base.conf.js'
      }
    }
  },
  rules: {
    'import/extensions': ['error', 'always', {js: 'never'}],
    'arrow-parens': ['error', 'as-needed'],
    'max-len': ['warn', {'code': 120}],
    'function-paren-newline': ['error', 'consistent'],
    'no-param-reassign': ['error', {
      props: true,
      ignorePropertyModificationsFor: [
        'state', // for state management
        'acc', // for reduce accumulators
        'e' // for e.returnvalue
      ]
    }],
    'import/no-extraneous-dependencies': ['error', {optionalDependencies: ['test/unit/index.js']}],
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',

    'react/jsx-filename-extension': ['off', { "extensions": [".js", ".jsx"] }]
  }
};
